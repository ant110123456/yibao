package com.yb.Controller;

import com.yb.dto.aggregatepay.AggregatePayDto;
import com.yb.dto.standardpay.StandardPayDto;
import com.yb.service.PayService;
import com.yb.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(value = "易宝支付支付接口", tags = {"易宝支付接口"})
@RestController
@RequestMapping("/api")
public class PayController {

    @Autowired
    private PayService payService;
    /**
     * 标准支付
     * @return
     */
    @ApiOperation(value = "标准支付", notes = "标准支付")
    @PostMapping("/standardPay")
    @ResponseBody
    public R standardPay(@RequestBody StandardPayDto standardPayDto){
        return R.successData(payService.standardPay(standardPayDto));
    }

    /**
     * 聚合支付
     * @return
     */
    @ApiOperation(value = "聚合支付", notes = "聚合支付")
    @PostMapping("/aggregatePay")
    public R aggregatePay(@RequestBody AggregatePayDto aggregatePayDto){
        return R.successData(payService.aggregatePay(aggregatePayDto));
    }




}
