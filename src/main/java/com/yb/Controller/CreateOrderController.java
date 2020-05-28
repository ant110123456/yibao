package com.yb.Controller;

import com.yb.dto.createorder.CreateOrderDto;
import com.yb.service.PayService;
import com.yb.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(value = "易宝支付预下单接口", tags = {"易宝支付接口"})
@RestController
@RequestMapping("/api")
public class CreateOrderController {
    @Autowired
    private PayService payService;

    /**
     * 预下单
     * @param createOrderDto
     * @return
     */
    @ApiOperation(value = "预下单", notes = "预下单")
    @PostMapping("/createOrder")
    @ResponseBody
    public R createOrder(@RequestBody CreateOrderDto createOrderDto){
        return R.successData(payService.createOrder(createOrderDto));
    }

}
