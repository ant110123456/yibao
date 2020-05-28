package com.yb.dto.aggregatepay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel("聚合支付扩展参数对象")
public class ExtParamMapDto implements Serializable {

    /**
     * 预备参数暂不使用
     */
    @ApiModelProperty(value = "预备参数暂不使用")
    private String specifyChannelCodes;
    /**
     * 订单优惠标记
     * 订单优惠标记，代金券或立减优惠功能参数
     */
    @ApiModelProperty(value = "订单优惠标记\n" +
            "     * 订单优惠标记，代金券或立减优惠功能参数")
    private String goodsTags;

    /**
     * 订单原价(元)
     * 使用微信优惠券时必填
      */
    @ApiModelProperty(value = "订单原价(元)\n" +
            "     * 使用微信优惠券时必填")
    private String costPrice;
    /**
     * 商品小票 ID
     * 使用微信优惠券时必填
     */
    @ApiModelProperty(value = "商品小票 ID\n" +
            "     * 使用微信优惠券时必填")
    private String receiptId;
    /**
     * 单品列表
     * 使用微信优惠券时必填
     * JSON 数组格式
     */
    @ApiModelProperty(value = "单品列表\n" +
            "     * 使用微信优惠券时必填\n" +
            "     * JSON 数组格式")
    private String goodsDetail;
    /**
     * 用于区分同样支付方式的线上和线下场景，一般聚合支付时候都需提供
     * 枚举：
     * 线下：XIANXIA
     * 线上：XIANSHANG
     *
     * 使用聚合支付时候必填，请根据真实交易场景进行入参，此参数作为筛选通道的标准。
     */
    @ApiModelProperty(value = " 用于区分同样支付方式的线上和线下场景，一般聚合支付时候都需提供\n" +
            "     * 枚举：\n" +
            "     * 线下：XIANXIA\n" +
            "     * 线上：XIANSHANG\n" +
            "     *\n" +
            "     * 使用聚合支付时候必填，请根据真实交易场景进行入参，此参数作为筛选通道的标准。")
    private String reportFee;

}
