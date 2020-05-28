package com.yb.util.ds;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class PayUrl {

    public static void main(String[] args) throws Exception {
//        System.out.println(PayUrl.createOrder());
        System.out.println(PayUrl.pay());
//        System.out.println(PayUrl.Jhpay());
    }



    /**
     * 创建订单获取token
     * @return
     * @throws Exception
     */
    public static String createOrder() throws Exception{
        //请求URI
//        String uri = "/yos/v1.0/sys/merchant/qual/upload";
        String key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDCC2oVe6OYd8ZtuhW9AN8wV9bat5wz3rva5H8iPAv99VQkORANnh6l+a7RNVfN9w+Yii6UeavhSsulgicDUngJdCHaPIsuXRWt26ejSsLeHmxXnWPG2AObZcnyYzUzwZ4MiAWJ6RcRrF7BZGpAPkBGK0kLBeZ9e8Ko8SgRUXzVHmPjg8oF5vV0xMNDj92X0oZBVfzt0rOSqlGVWWgRkgIBz6CZKiy9pmLnKOnpG5qOOdiTdth+DsAR7ABK4lzkPeesAsR1VzP4EqW/TKC64YKhMA3N1ovfMC9EpQ2oCPwvairAsQcB/pvXxHBXttF/BTrTw/Ks9tkh2QMRBvZGHpfAgMBAAECggEABr/1GibTEyKXi4uQjGolg9eyQdNPgiAuBQdVjdzAAriRlITiPSyRKD+K8zqogy8teUk1L+PoLkJ95vhzmRZWJ+XKyC7vyr4C8DSizigXf4/FNQ3YoHaYjCW5E6OeTZgcjTSH0pxYKyi5G809o6cZLKVIxgQ/cv7oQXQOPPNUlyQ/aBl1c1cSDAWbyX7BDduqZmk5BPnyud9vtEOuKAQqFwPfy3/ZfkibilUYcvtNqRSUl/7VinZeAisSXPbKre2qk5ll/YXeavxkBZxdq6/JS5O4ivBtrQy9Fnil+7hBe6Qfw4Lt7Fv5NdObJIVwzq7cMTHGxnUaf3MNRpdkHvJsgQKBgQDrPt7vI5BuMvzM6wILXnxQ76quYPFE9nJ1glYPCpCAirKP5kAEjmH/2mJ4IqTi2uT5pgoPb0zGspL7R1tsJcSgGa98qEgyeG1n+6C9M2a+vmht8VTj0nrZeIIigQH2dF16K8c87H1jgg0N5VrjG+pRKG23dQ0rX4O0B+3MoHUN3QKBgQCOl5hCO2OVvillvvk0Wabll3ytWYZZRN/4COWtDaXY10RkpeBRyDZvAUE9Gyi/ZegfvTfZzV5gPnVFtXqbIEY8u0xD4MQSAuncY4V16cv70cvu4u3xGEZKgzgk8TOfPNxInCWUles6lP451x5B3HIAa63Ii1j3Qd0ceuI8iqT7awKBgQCh8M7Q+r+DTPBANItcvjeAE+yATFXqrmjOweFyS0h8ZH5VlyB8wnNuCKz+nIK7dApqXUXRqEHHCskp1850nW9E80md286Ph91w1oSpmkfhiPwkqxxQFOXi7RVQoVRzj1mGL7rhEr+ij7Vi2n99lgrwwY792sMtF3x3o3mtAsxxtQKBgAf5YFFr4tDP9p6zBFqyHMxAIX/MPuAlIuVLEhUQa1LqDvAV+qp4KNsiVdSl/Sxe9ZE40rPCcWGufH5ufLHKJ0NkMgqlujFLqmphwmfqsDaf7+inFilicyPdnLksJ/fivmrtGIjrrWD0ThdL+WwzeMifPPO3Hz2MmGHsWVSLaFiLAoGBANL7mp+y+J9Olx9LPjR14lanOg4PhnhIJ/CQt41WWgkEbSXfign0LaYwJQ2ly6y8KoaVPN/VeICTQ9RXsvIAwUmy0YB4hvRS6kfsdsP+9MWMooecsnsz+fUgY+Ff6pJL3dhnr0cPqiB0J0xH2gMD80i9QFUfaWAmLD7KvB1y3XA4";
        //step1  生成yop请求对象
        //arg0:appkey（举例授权扣款是SQKK+商户编号，亿企通是OPR:+商户编号，具体是什么请参考自己开通产品的手册。
        //arg1:商户私钥，不传这个参数的时候，默认使用配置文件里的isv_private_key
        YopRequest request = new YopRequest("OPR:10014929805",key);
        //step2 配置参数
        //arg0:参数名
        //arg1:参数值
        request.addParam("parentMerchantNo", "10014929805");
        request.addParam("merchantNo", "10015004197");
        request.addParam("orderId", UUID.randomUUID().toString().replaceAll("-", ""));
        request.addParam("orderAmount", "0.03");
        request.addParam("notifyUrl", "https://www.baidu.com");
        request.addParam("goodsParamExt", "{\"goodsName\":\"abc商品名称\" ,\"goodsDesc\" : \"商品描 述\" }");
        //request.addParam("fundProcessType", "");
        //request.addParam("divideDetail", "");
        //生成 hmac
        //step1 按顺序将关键请求参数（具体有哪些参考手册）拼接成字符串
        String inputHmacStr ="parentMerchantNo=" + request.getParamValue("parentMerchantNo") + "&" +
                "merchantNo=" + request.getParamValue("merchantNo") + "&" +
                "orderId=" + request.getParamValue("orderId") + "&" +
                "orderAmount=" + request.getParamValue("orderAmount") + "&" +
                "notifyUrl=" + request.getParamValue("notifyUrl");
        //step2 获取子商户密钥
        String hmackey = runtestgethmackey.getHmacKey("10015004197","10014929805");
        if (hmackey != null && !hmackey.equals("")) {
        } else {
            System.out.println("get hmackey error");
        }
        //step3获取hmac
        String hmac = runtesthmac.getHmac(inputHmacStr, hmackey);
        if (hmac != null && !hmac.equals("")) {
        } else {
            System.out.println("get hmac error");
        }
        request.addParam("hmac", hmac);
        //step3 发起请求
        //arg0:接口的uri（参见手册）
        //arg1:配置好参数的请求对象
//        System.out.println(request.getParams().toString());
        YopResponse response = YopRsaClient.post("/rest/v1.0/sys/trade/order", request);
        if ("SUCCESS".equals(response.getState())){
            JSONObject parse = JSON.parseObject(response.getStringResult());
           return parse.getString("token");
        }
        return null;
    }

    public static String payUrl(String token){
        String appkey = "OPR:10014929805";
        String OPRkey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDCC2oVe6OYd8ZtuhW9AN8wV9bat5wz3rva5H8iPAv99VQkORANnh6l+a7RNVfN9w+Yii6UeavhSsulgicDUngJdCHaPIsuXRWt26ejSsLeHmxXnWPG2AObZcnyYzUzwZ4MiAWJ6RcRrF7BZGpAPkBGK0kLBeZ9e8Ko8SgRUXzVHmPjg8oF5vV0xMNDj92X0oZBVfzt0rOSqlGVWWgRkgIBz6CZKiy9pmLnKOnpG5qOOdiTdth+DsAR7ABK4lzkPeesAsR1VzP4EqW/TKC64YKhMA3N1ovfMC9EpQ2oCPwvairAsQcB/pvXxHBXttF/BTrTw/Ks9tkh2QMRBvZGHpfAgMBAAECggEABr/1GibTEyKXi4uQjGolg9eyQdNPgiAuBQdVjdzAAriRlITiPSyRKD+K8zqogy8teUk1L+PoLkJ95vhzmRZWJ+XKyC7vyr4C8DSizigXf4/FNQ3YoHaYjCW5E6OeTZgcjTSH0pxYKyi5G809o6cZLKVIxgQ/cv7oQXQOPPNUlyQ/aBl1c1cSDAWbyX7BDduqZmk5BPnyud9vtEOuKAQqFwPfy3/ZfkibilUYcvtNqRSUl/7VinZeAisSXPbKre2qk5ll/YXeavxkBZxdq6/JS5O4ivBtrQy9Fnil+7hBe6Qfw4Lt7Fv5NdObJIVwzq7cMTHGxnUaf3MNRpdkHvJsgQKBgQDrPt7vI5BuMvzM6wILXnxQ76quYPFE9nJ1glYPCpCAirKP5kAEjmH/2mJ4IqTi2uT5pgoPb0zGspL7R1tsJcSgGa98qEgyeG1n+6C9M2a+vmht8VTj0nrZeIIigQH2dF16K8c87H1jgg0N5VrjG+pRKG23dQ0rX4O0B+3MoHUN3QKBgQCOl5hCO2OVvillvvk0Wabll3ytWYZZRN/4COWtDaXY10RkpeBRyDZvAUE9Gyi/ZegfvTfZzV5gPnVFtXqbIEY8u0xD4MQSAuncY4V16cv70cvu4u3xGEZKgzgk8TOfPNxInCWUles6lP451x5B3HIAa63Ii1j3Qd0ceuI8iqT7awKBgQCh8M7Q+r+DTPBANItcvjeAE+yATFXqrmjOweFyS0h8ZH5VlyB8wnNuCKz+nIK7dApqXUXRqEHHCskp1850nW9E80md286Ph91w1oSpmkfhiPwkqxxQFOXi7RVQoVRzj1mGL7rhEr+ij7Vi2n99lgrwwY792sMtF3x3o3mtAsxxtQKBgAf5YFFr4tDP9p6zBFqyHMxAIX/MPuAlIuVLEhUQa1LqDvAV+qp4KNsiVdSl/Sxe9ZE40rPCcWGufH5ufLHKJ0NkMgqlujFLqmphwmfqsDaf7+inFilicyPdnLksJ/fivmrtGIjrrWD0ThdL+WwzeMifPPO3Hz2MmGHsWVSLaFiLAoGBANL7mp+y+J9Olx9LPjR14lanOg4PhnhIJ/CQt41WWgkEbSXfign0LaYwJQ2ly6y8KoaVPN/VeICTQ9RXsvIAwUmy0YB4hvRS6kfsdsP+9MWMooecsnsz+fUgY+Ff6pJL3dhnr0cPqiB0J0xH2gMD80i9QFUfaWAmLD7KvB1y3XA4";

        StringBuffer url = new StringBuffer();
        //地址
        url.append("https://cash.yeepay.com/cashier/std");
        //参数

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append( "merchantNo=" + "10014929805" );
        stringBuilder.append("&" + "token=" + "39C61CACC0B1F2288004C0355D06A104006C042A985D97BFE6BB23450C364FE7" );
        stringBuilder.append("&" + "timestamp=" + String.valueOf(System.currentTimeMillis()/1000) );
        stringBuilder.append("&" + "directPayType=" + "" );
        stringBuilder.append("&" + "cardType=" + "" );
        stringBuilder.append("&" + "userNo=" + "xk001" );
        stringBuilder.append("&" + "userType=" + "USER_ID" );
        stringBuilder.append("&" + "ext=" + "{\"appId\":\"\",\"openId\":\"\",\"clientId\":\"\"}" );

        String sign = runtestgetpayurl.getSign(stringBuilder.toString(),appkey,OPRkey);
        url.append("?sign="+sign+"&"+stringBuilder);
        return url.toString();
    }


    /**
     * 获取支付连接
     * @return
     */
    public static String pay(){
        try {
            return PayUrl.payUrl(PayUrl.createOrder());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取聚合支付连接
     * @return
     */
    public static String Jhpay(){
        try {
            return PayUrl.JHpay(PayUrl.createOrder());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String JHpay(String token) throws Exception{
        //请求URI
        String uri = "/rest/v1.0/nccashierapi/api/pay";

        String key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDCC2oVe6OYd8ZtuhW9AN8wV9bat5wz3rva5H8iPAv99VQkORANnh6l+a7RNVfN9w+Yii6UeavhSsulgicDUngJdCHaPIsuXRWt26ejSsLeHmxXnWPG2AObZcnyYzUzwZ4MiAWJ6RcRrF7BZGpAPkBGK0kLBeZ9e8Ko8SgRUXzVHmPjg8oF5vV0xMNDj92X0oZBVfzt0rOSqlGVWWgRkgIBz6CZKiy9pmLnKOnpG5qOOdiTdth+DsAR7ABK4lzkPeesAsR1VzP4EqW/TKC64YKhMA3N1ovfMC9EpQ2oCPwvairAsQcB/pvXxHBXttF/BTrTw/Ks9tkh2QMRBvZGHpfAgMBAAECggEABr/1GibTEyKXi4uQjGolg9eyQdNPgiAuBQdVjdzAAriRlITiPSyRKD+K8zqogy8teUk1L+PoLkJ95vhzmRZWJ+XKyC7vyr4C8DSizigXf4/FNQ3YoHaYjCW5E6OeTZgcjTSH0pxYKyi5G809o6cZLKVIxgQ/cv7oQXQOPPNUlyQ/aBl1c1cSDAWbyX7BDduqZmk5BPnyud9vtEOuKAQqFwPfy3/ZfkibilUYcvtNqRSUl/7VinZeAisSXPbKre2qk5ll/YXeavxkBZxdq6/JS5O4ivBtrQy9Fnil+7hBe6Qfw4Lt7Fv5NdObJIVwzq7cMTHGxnUaf3MNRpdkHvJsgQKBgQDrPt7vI5BuMvzM6wILXnxQ76quYPFE9nJ1glYPCpCAirKP5kAEjmH/2mJ4IqTi2uT5pgoPb0zGspL7R1tsJcSgGa98qEgyeG1n+6C9M2a+vmht8VTj0nrZeIIigQH2dF16K8c87H1jgg0N5VrjG+pRKG23dQ0rX4O0B+3MoHUN3QKBgQCOl5hCO2OVvillvvk0Wabll3ytWYZZRN/4COWtDaXY10RkpeBRyDZvAUE9Gyi/ZegfvTfZzV5gPnVFtXqbIEY8u0xD4MQSAuncY4V16cv70cvu4u3xGEZKgzgk8TOfPNxInCWUles6lP451x5B3HIAa63Ii1j3Qd0ceuI8iqT7awKBgQCh8M7Q+r+DTPBANItcvjeAE+yATFXqrmjOweFyS0h8ZH5VlyB8wnNuCKz+nIK7dApqXUXRqEHHCskp1850nW9E80md286Ph91w1oSpmkfhiPwkqxxQFOXi7RVQoVRzj1mGL7rhEr+ij7Vi2n99lgrwwY792sMtF3x3o3mtAsxxtQKBgAf5YFFr4tDP9p6zBFqyHMxAIX/MPuAlIuVLEhUQa1LqDvAV+qp4KNsiVdSl/Sxe9ZE40rPCcWGufH5ufLHKJ0NkMgqlujFLqmphwmfqsDaf7+inFilicyPdnLksJ/fivmrtGIjrrWD0ThdL+WwzeMifPPO3Hz2MmGHsWVSLaFiLAoGBANL7mp+y+J9Olx9LPjR14lanOg4PhnhIJ/CQt41WWgkEbSXfign0LaYwJQ2ly6y8KoaVPN/VeICTQ9RXsvIAwUmy0YB4hvRS6kfsdsP+9MWMooecsnsz+fUgY+Ff6pJL3dhnr0cPqiB0J0xH2gMD80i9QFUfaWAmLD7KvB1y3XA4";

        //step1  生成yop请求对象
        //arg0:appkey（举例授权扣款是SQKK+商户编号，亿企通是OPR:+商户编号，具体是什么请参考自己开通产品的手册。
        //arg1:商户私钥，不传这个参数的时候，默认使用配置文件里的isv_private_key
        YopRequest request = new YopRequest("OPR:10014929805",key);
        //step2 配置参数
        //arg0:参数名
        //arg1:参数值
        request.addParam("token", token);
        request.addParam("payTool", "MSCANPAY");
        request.addParam("payType", "ALIPAY");
        request.addParam("userNo", "1");
        request.addParam("payEmpowerNo","123");
        request.addParam("merchantStoreNo","132465");
        request.addParam("merchantTerminalId","123");
        request.addParam("userType", "USER_ID");
        request.addParam("userIp", "127.0.0.1");
        request.addParam("version","1.0");
        request.addParam("extParamMap","");
        YopResponse response = YopRsaClient.post(uri, request);
        System.out.println(response.toString());
        return JSON.toJSONString(response);

    }



}
