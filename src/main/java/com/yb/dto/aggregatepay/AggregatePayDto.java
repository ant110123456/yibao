package com.yb.dto.aggregatepay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel("聚合支付参数对象")
public class AggregatePayDto implements Serializable {
    /**
     * 预下单token
     */
    @ApiModelProperty(value = "预下单token")
    private String token;

    /**
     * 收单商户商编
     */
    @ApiModelProperty(value = "收单商户商编")
    private String merchantNo;
    /**
     * 支付工具
     * SCCANPAY（用户扫码支付）
     * MSCANPAY（商户扫码支付）
     * WECHAT_OPENID（公众号支付）
     * ZFB_SHH（支付宝生活号）
     * MINI_PROGRAM（微信小程序）
     * EWALLET（SDK 支付）
     * JSPAY（JS 支付）
     */
    @ApiModelProperty(value = "支付工具\n" +
            "     * SCCANPAY（用户扫码支付）\n" +
            "     * MSCANPAY（商户扫码支付）\n" +
            "     * WECHAT_OPENID（公众号支付）\n" +
            "     * ZFB_SHH（支付宝生活号）\n" +
            "     * MINI_PROGRAM（微信小程序）\n" +
            "     * EWALLET（SDK 支付）\n" +
            "     * JSPAY（JS 支付）")
    private String payTool;

    /**
     * 支付类型
     * 可选枚举：
     * WECHAT：微信
     * ALIPAY：支付宝
     * UPOP:银联支付
     *
     * 此参数请根据商户在易宝开通的产品来确定传值，如果商户未开所传参数所对应的支付类型的话，接口会报错
     */
    @ApiModelProperty(value = "支付类型\n" +
            "     * 可选枚举：\n" +
            "     * WECHAT：微信\n" +
            "     * ALIPAY：支付宝\n" +
            "     * UPOP:银联支付\n" +
            "     *\n" +
            "     * 此参数请根据商户在易宝开通的产品来确定传值，如果商户未开所传参数所对应的支付类型的话，接口会报错")
    private String payType;

    /**
     * 用户标识，必填，用户在商户的唯一标识
     */
    @ApiModelProperty(value = "用户标识，必填，用户在商户的唯一标识")
    private String userNo;
    /**
     * 用户标识类型
     * IMEI(IMEI)
     * MAC(MAC 地址)
     * USER_ID(用户 ID)
     * EMAIL(用户 Email)
     * PHONE(用户手机号)
     * ID_CARD(用户身份证号)
     */
    @ApiModelProperty(value = "用户标识类型\n" +
            "     * IMEI(IMEI)\n" +
            "     * MAC(MAC 地址)\n" +
            "     * USER_ID(用户 ID)\n" +
            "     * EMAIL(用户 Email)\n" +
            "     * PHONE(用户手机号)\n" +
            "     * ID_CARD(用户身份证号)")
    private String userType;
    /**
     * 商家公众号 ID
     * 公众号或者小程序支付时必填，微信平台服务号，即公众号或小程序的的 appId，登录微信平台开发者中心－配置中查看 appId
     */
    @ApiModelProperty(value = "商家公众号 ID\n" +
            "     * 公众号或者小程序支付时必填，微信平台服务号，即公众号或小程序的的 appId，登录微信平台开发者中心－配置中查看 appId")
    private String appId;

    /**
     * 用户 OPENID
     * 公众号或者小程序支付时必填用户 OPENID,支付宝生活号支付必填用户支付宝 ID
     */
    @ApiModelProperty(value = "用户 OPENID\n" +
            "     * 公众号或者小程序支付时必填用户 OPENID,支付宝生活号支付必填用户支付宝 ID")
    private String openId;
    /**
     * 授权码
     * 微信或者支付宝的用户支付二位码，用户被扫时必填
     */
    @ApiModelProperty(value = "授权码\n" +
            "     * 微信或者支付宝的用户支付二位码，用户被扫时必填")
    private String payEmpowerNo;

    /**
     * 商户设备号
     * 用户被扫时，上传商户的扫码设备编号（商户自定义，每台设备唯一）
     * 使用银联扫码时，此参数必须为 8 位定长的字母或数字
     */
    @ApiModelProperty(value = "商户设备号\n" +
            "     * 用户被扫时，上传商户的扫码设备编号（商户自定义，每台设备唯一）\n" +
            "     * 使用银联扫码时，此参数必须为 8 位定长的字母或数字")
    private String merchantTerminalId;
    /**
     * 门店号
     * 用户被扫时，上传商户的店面编码（商户自定义，每个商户唯一）
     */
    @ApiModelProperty(value = "门店号\n" +
            "     * 用户被扫时，上传商户的店面编码（商户自定义，每个商户唯一）")
    private String merchantStoreNo;

    /**
     * 用户 IP
     * 用户真实 IP 地址
     */
    @ApiModelProperty(value = "用户 IP\n" +
            "     * 用户真实 IP 地址")
    private String userIp;

    /**
     * 接口版本号
     * 固定 1.0
     */
    @ApiModelProperty(value = "接口版本号\n" +
            "     * 固定 1.0")
    private String version;

    /**
     * 扩展参数
     * 扩展参数，请将 Map 转换为 JSON 字符串后传入，Map 支持的具体 key 见下表
     */
    @ApiModelProperty(value = " 扩展参数\n" +
            "     * 扩展参数，请将 Map 转换为 JSON 字符串后传入，Map 支持的具体 key 见下表")
    private ExtParamMapDto extParamMap;

}
