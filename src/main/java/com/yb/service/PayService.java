package com.yb.service;

import com.yb.dto.standardpay.Ext;
import com.yb.exception.BaseException;
import com.yb.util.ds.runtestgethmackey;
import com.yb.util.ds.runtestgetpayurl;
import com.yb.util.ds.runtesthmac;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yb.config.PayConfig;
import com.yb.dto.aggregatepay.AggregatePayDto;
import com.yb.dto.createorder.CreateOrderDto;
import com.yb.dto.standardpay.StandardPayDto;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class PayService {

    @Autowired
    private PayConfig payConfig;

    /**
     * 预下单
     *
     * @param createOrderDto
     * @return
     */
    public String createOrder(CreateOrderDto createOrderDto) {
        log.info("createOrder parmas:{}", createOrderDto);
        YopRequest request = new YopRequest("OPR:" + payConfig.getP1_MerId(), payConfig.getKey());
        request.addParam("parentMerchantNo", payConfig.getP1_MerId());
        request.addParam("merchantNo", createOrderDto.getMerchantNo());
        request.addParam("orderId", createOrderDto.getOrderId());
        request.addParam("orderAmount", createOrderDto.getOrderAmount());
        request.addParam("notifyUrl", createOrderDto.getNotifyUrl());
        request.addParam("goodsParamExt", JSON.toJSONString(createOrderDto.getGoodsDto()));
        String inputHmacStr = "parentMerchantNo=" + request.getParamValue("parentMerchantNo") + "&" +
                "merchantNo=" + request.getParamValue("merchantNo") + "&" +
                "orderId=" + request.getParamValue("orderId") + "&" +
                "orderAmount=" + request.getParamValue("orderAmount") + "&" +
                "notifyUrl=" + request.getParamValue("notifyUrl");
        try {
            String hmackey = runtestgethmackey.getHmacKey(createOrderDto.getMerchantNo(), payConfig.getP1_MerId());
            if (StringUtils.isEmpty(hmackey)) {
                log.error("get hmackey error");
                throw new BaseException("get hmackey error");
            }
            String hmac = runtesthmac.getHmac(inputHmacStr, hmackey);
            if (StringUtils.isEmpty(hmac)) {
                log.error("get hmac error");
                throw new BaseException("get hmac error");
            }
            request.addParam("hmac", hmac);
            YopResponse response = YopRsaClient.post("/rest/v1.0/sys/trade/order", request);
            if ("SUCCESS".equals(response.getState())) {
                JSONObject parse = JSON.parseObject(response.getStringResult());
                return parse.getString("token");
            } else {
                log.error("createOrder:create order fail");
                throw new BaseException("create order fail");
            }
        } catch (Exception e) {
            log.error("createOrder:{}", e.getMessage());
            e.printStackTrace();
            throw new BaseException(e.getMessage());
        }
    }

    /**
     * 标准支付
     *
     * @param standardPayDto
     * @return
     */
    public String standardPay(StandardPayDto standardPayDto) {
        log.info("standardPay parmas:{}", standardPayDto);
        try {
            String appkey = "OPR:" + standardPayDto.getMerchantNo();
            String OPRkey = payConfig.getKey();
            StringBuffer url = new StringBuffer();
            url.append("https://cash.yeepay.com/cashier/std");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("merchantNo=" + standardPayDto.getMerchantNo());
            stringBuilder.append("&" + "token=" + standardPayDto.getToken());
            stringBuilder.append("&" + "timestamp=" + String.valueOf(System.currentTimeMillis() / 1000));
            stringBuilder.append("&" + "directPayType=" + standardPayDto.getDirectPayType());
            stringBuilder.append("&" + "cardType=" + standardPayDto.getCardType());
            stringBuilder.append("&" + "userNo=" + standardPayDto.getUserNo());
            stringBuilder.append("&" + "userType=" + standardPayDto.getUserType());
            String ext = standardPayDto.getExt() != null ? JSON.toJSONString(standardPayDto.getExt()) : JSON.toJSONString(new Ext());
            stringBuilder.append("&" + "ext=" + ext);
            String sign = runtestgetpayurl.getSign(stringBuilder.toString(), appkey, OPRkey);
            url.append("?sign=" + sign + "&" + stringBuilder);
            log.info(url.toString());
            return url.toString();
        } catch (Exception e) {
            log.error("standardPay:{}", e.getMessage());
            e.printStackTrace();
            throw new BaseException(e.getMessage());
        }

    }


    /**
     * 聚合支付
     *
     * @param aggregatePayDto
     * @return
     */
    public String aggregatePay(AggregatePayDto aggregatePayDto) {
        log.info("aggregatePay parmas:{}", aggregatePayDto);
        try {
            String uri = "/rest/v1.0/nccashierapi/api/pay";
            String key = payConfig.getKey();
            YopRequest request = new YopRequest("OPR:" + aggregatePayDto.getMerchantNo(), key);
            request.addParam("token", aggregatePayDto.getToken());
            request.addParam("payTool", aggregatePayDto.getPayTool());
            request.addParam("payType", aggregatePayDto.getPayType());
            request.addParam("userNo", aggregatePayDto.getUserNo());
            request.addParam("payEmpowerNo", aggregatePayDto.getPayEmpowerNo());
            request.addParam("merchantStoreNo", aggregatePayDto.getMerchantStoreNo());
            request.addParam("merchantTerminalId", aggregatePayDto.getMerchantTerminalId());
            request.addParam("userType", aggregatePayDto.getUserType());
            request.addParam("userIp", aggregatePayDto.getUserIp());
            request.addParam("version", aggregatePayDto.getVersion());
            request.addParam("extParamMap", aggregatePayDto.getExtParamMap() != null ? JSON.toJSONString(aggregatePayDto.getExtParamMap()) : "");
            YopResponse response = YopRsaClient.post(uri, request);
            log.info(response.toString());
            return JSON.toJSONString(response);
        } catch (Exception e) {
            log.error("aggregatePay:{}", e.getMessage());
            e.printStackTrace();
            throw new BaseException(e.getMessage());
        }
    }

}
