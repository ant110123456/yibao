package com.yb.dto.standardpay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel("标准支付扩展参数对象")
public class Ext implements Serializable {

    /**
     * 微信公众号 openId
     */
    @ApiModelProperty(value = "微信公众号 openId")
    private String openId;

    /**
     * 微信公众号 appId
     */
    @ApiModelProperty(value = "微信公众号 appId")
    private  String appId;

    /**
     * 网银对公银行客户号
     */
    @ApiModelProperty(value = "网银对公银行客户号")
    private String clientId;
    /**
     * 支付宝生活号支付所需 userId
     */
    @ApiModelProperty(value = "支付宝生活号支付所需 userId")
    private String aliUserId;
    /**
     * 支付宝生活号 appId
     */
    @ApiModelProperty(value = "支付宝生活号 appId")
    private String aliAppId;
    /**
     * 报备费率，用于区分线上线下，可选取值：XIANSHANG
     */
    @ApiModelProperty(value = "报备费率，用于区分线上线下，可选取值：XIANSHANG")
    private String reportFee;

}
