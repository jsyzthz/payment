package me.jtx.robinia.payment.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import me.jtx.robinia.payment.model.PayType;

@Controller
@RequestMapping("/v1/pay")
public class PaymentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @RequestMapping("/test")
    public String welcome() {
        return "/views/payment/qrpay";
    }

    @RequestMapping("/qr")
    public String pay(@RequestHeader(name = "User-Agent") String userAgent, ModelMap model) {
        String view = "/views/payment/qrpay";
        if (StringUtils.isBlank(userAgent)) {
            String errorMessage = "User-Agent为空！";
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }
        PayType payType = null;
        if (userAgent.contains("Alipay")) {
            payType = PayType.ALIPAY;
        } else if (userAgent.contains("wx")) {
            payType = PayType.WEPAY;
        }

        if (payType == null) {
            String errorMessage = "请用微信或支付宝扫码";
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }

        return view;
    }

}
