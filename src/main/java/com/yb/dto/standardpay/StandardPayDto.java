package com.yb.dto.standardpay;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("标准支付参数对象")
public class StandardPayDto implements Serializable {

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
     * 直连参数 设置该参数后，直接调用支付工具，不显示易宝移动收银台页面。枚举值：
     * WECHAT：微信 H5 支付
     * ALIPAY：支付宝 H5 支付
     * YJZF：易宝一键支付
     * CFL:分期支付
     * DBFQ:担保分期
     * 网银支付时通道编码可参考[网银直直联参数]，但参数参与排序生成签名 sign；公众号不能直连
     */
    @ApiModelProperty(value = "直连参数 设置该参数后，直接调用支付工具，不显示易宝移动收银台页面。枚举值：\n" +
            "     * WECHAT：微信 H5 支付\n" +
            "     * ALIPAY：支付宝 H5 支付\n" +
            "     * YJZF：易宝一键支付\n" +
            "     * CFL:分期支付\n" +
            "     * DBFQ:担保分期\n" +
            "     * 网银支付时通道编码可参考[网银直直联参数]，但参数参与排序生成签名 sign；公众号不能直连")
    private String directPayType;

    /**
     * 卡种
     * 限制交易的卡类型，对快捷和网银支付有效，对钱包和扫码支付无效
     * 枚举值：
     * DEBIT：借记卡
     * CREDIT：信用卡
     * 该项目不传时，不限制支付卡种值可不传，但参数参与排序生成签名 sign
     */
    @ApiModelProperty(value = "卡种\n" +
            "     * 限制交易的卡类型，对快捷和网银支付有效，对钱包和扫码支付无效\n" +
            "     * 枚举值：\n" +
            "     * DEBIT：借记卡\n" +
            "     * CREDIT：信用卡\n" +
            "     * 该项目不传时，不限制支付卡种值可不传，但参数参与排序生成签名 sign")
    private String cardType;


    /**
     * 用户标识
     * 用户标识，必填，用户在商户的唯一标识。
     */
    @ApiModelProperty(value = " 用户标识\n" +
            "     * 用户标识，必填，用户在商户的唯一标识。")
    private String userNo;
    /**
     * 用户标识类型
     * IMEI：IMEI
     * Mac：Mac 地址
     * USER_ID：用户 ID
     * EMAIL：用户 Email
     * PHONE：用户手机号
     * ID_CARD：用户身份证号
     */
    @ApiModelProperty(value = "用户标识类型\n" +
            "     * IMEI：IMEI\n" +
            "     * Mac：Mac 地址\n" +
            "     * USER_ID：用户 ID\n" +
            "     * EMAIL：用户 Email\n" +
            "     * PHONE：用户手机号\n" +
            "     * ID_CARD：用户身份证号")
    private String userType;

    /**
     * 扩展字段
     * 格式：微信公众号支付实现方式：{“appId”:”wx9e13bd68a8f1921e”,”openId”:”zml_wechat”,”clientId”:”*****”}
     */
    @ApiModelProperty(value = "扩展字段\n" +
            "     * 格式：微信公众号支付实现方式：{“appId”:”wx9e13bd68a8f1921e”,”openId”:”zml_wechat”,”clientId”:”*****”}")
    private Ext ext;

    /**
     * 签名
     * 以参数编号为顺序，调用 YOP 的签名方法进行签名，非必传参数项目也要将 key 加入签名签名数据样例：merchantNo=xxxxx&token=xxxx× tamp=xxxx&cardType=&userNo
     */
    @ApiModelProperty(value = " 签名\n" +
            "     * 以参数编号为顺序，调用 YOP 的签名方法进行签名，非必传参数项目也要将 key 加入签名签名数据样例：merchantNo=xxxxx&token=xxxx× tamp=xxxx&cardType=&userNo")
    private String sign;


}
