package com.yb.dto.createorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建订单
 */
@ApiModel("创建订单参数对象")
@Data
public class CreateOrderDto implements Serializable {
    /**
     * 收单商户商编
     */
    @ApiModelProperty(value = "收单商户商编")
    private String merchantNo;
    /**
     * order编号
     */
    @ApiModelProperty(value = "order编号")
    private String orderId;
    /**
     * 订单金额(0.03)
     */
    @ApiModelProperty(value = "订单金额(0.03)")
    private String orderAmount;

    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调地址")
    private String notifyUrl;


    /**
     * 商品详细
     */
    @ApiModelProperty(value = "商品详细")
    private GoodsDto goodsDto;



}
