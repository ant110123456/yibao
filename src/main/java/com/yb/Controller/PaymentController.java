package com.yb.Controller;

import com.yb.config.PayConfig;
import com.yb.util.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/web")
public class PaymentController {
    @Autowired
    private PayConfig payConfig;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 网页选择银行支付
     * @param request
     * @param response
     * @param modelMap
     * @return
     */
    @PostMapping("/paymentRequest")
    public ModelAndView paymentRequest(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String orderid = request.getParameter("orderid");//订单号
        String amount = request.getParameter("amount");//支付金额
        String pd_FrpId = request.getParameter("pd_FrpId");//选择的支付银行
        String p1_MerId = payConfig.getP1_MerId();
        String keyValue = payConfig.getKeyValue();
        String merchantCallbackURL = payConfig.getMerchantCallbackURL();
        String messageType = "Buy"; // 请求命令,在线支付固定为Buy
        String currency = "CNY"; // 货币单位
        String productDesc = ""; // 商品描述
        String productCat = ""; // 商品种类
        String productId = ""; // 商品ID
        String addressFlag = "0"; // 需要填写送货信息 0：不需要 1:需要
        String sMctProperties = ""; // 商家扩展信息
        String pr_NeedResponse = "0"; // 应答机制
        String md5hmac = PaymentUtil.buildHmac(messageType, p1_MerId, orderid, amount, currency,
                productId, productCat, productDesc, merchantCallbackURL, addressFlag, sMctProperties,
                pd_FrpId, pr_NeedResponse, keyValue);
        modelMap.addAttribute("messageType", messageType);
        modelMap.addAttribute("merchantID", p1_MerId);
        modelMap.addAttribute("orderId", orderid);
        modelMap.addAttribute("amount", amount);
        modelMap.addAttribute("currency", currency);
        modelMap.addAttribute("productId", productId);
        modelMap.addAttribute("productCat", productCat);
        modelMap.addAttribute("productDesc", productDesc);
        modelMap.addAttribute("merchantCallbackURL", merchantCallbackURL);
        modelMap.addAttribute("addressFlag", addressFlag);
        modelMap.addAttribute("sMctProperties", sMctProperties);
        modelMap.addAttribute("frpId", pd_FrpId);
        modelMap.addAttribute("pr_NeedResponse", pr_NeedResponse);
        modelMap.addAttribute("hmac", md5hmac);
        return new ModelAndView("/connection",modelMap);

    }

    /**
     * 支付完成跳转页面通知
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/paymentResponse")
    public ModelAndView paymentResponse(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("paymentResult");
        String merchantID = payConfig.getP1_MerId();//商家ID
        String keyValue = payConfig.getKeyValue();//商家密钥
        String sCmd = request.getParameter("r0_Cmd");//业务类型
        String sResultCode = request.getParameter("r1_Code");//扣款结果 ，1表示扣款成功
        String sTrxId = request.getParameter("r2_TrxId");//YeePay易宝交易订单号
        String amount = request.getParameter("r3_Amt");//扣款金额，交易结束后，YeePay易宝交易系统将实际扣费金额返回给商户
        String currency = request.getParameter("r4_Cur");//交易币种，人民币为CNY;
        String productId = request.getParameter("r5_Pid");//商品ID
        String orderId = request.getParameter("r6_Order");//商户订单号
        String userId = request.getParameter("r7_Uid");//YeePay易宝会员ID
        String mp = request.getParameter("r8_MP");//商户扩展信息，可以任意填写1K的字符串，交易返回时将原样返回
        String bType = request.getParameter("r9_BType");//交易结果通知类型 1：交易成功回调（浏览器重定向） 2:交易成功主动通知（服务器点对点通讯）
        String rb_BankId = request.getParameter("rb_BankId");//支付银行
        String rb_PayDate = request.getParameter("rp_PayDate");//在银行支付时的时间
        String hmac = request.getParameter("hmac");//MD5交易签名
        System.out.println("交易签名---->" + hmac);
        boolean result = PaymentUtil.verifyCallback(hmac, merchantID, sCmd, sResultCode, sTrxId, amount, currency, productId, orderId, userId, mp, bType, keyValue);
        if (result) {
            if ("1".equals(sResultCode)) {
                //把数据库中用户的订单支付状态设置为已完成支付
                String message = "订单号:" + orderId + "的订单支付成功了";
                message += ",用户支付了" + amount + "元";
                message += ",交易结果通知类型：";
                if ("1".equals(bType)) {
                    message += "浏览器重定向";
                } else if ("2".equals(bType)) {
                    message += "易宝支付网关后台程序通知";
                }
                message += ",易宝订单系统中的订单号：" + sTrxId;
                mv.addObject("message",message);
            } else {
                mv.addObject("message","用户支付失败");
            }
        } else {
            mv.addObject("message","数据来源不合法");
        }
        return mv;
    }

}
