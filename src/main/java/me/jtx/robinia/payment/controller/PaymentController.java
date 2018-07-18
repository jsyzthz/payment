package me.jtx.robinia.payment.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import me.jtx.robinia.payment.model.Order;
import me.jtx.robinia.payment.model.OrderStatus;
import me.jtx.robinia.payment.model.PayType;
import me.jtx.robinia.payment.trade.alipay.config.ZhifubaoConfig;

@Controller
@RequestMapping("/v1/pay")
public class PaymentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private ZhifubaoConfig config;
    @RequestMapping("/test")
    public String welcome() {
        return "/views/payment/test";
    }

    @RequestMapping("/qr")
    public String pay(@RequestHeader(name = "User-Agent") String userAgent, ModelMap model) {
        String view = "/views/payment/qrpay";
        Order order = new Order();
        model.put("order", order);
        if (StringUtils.isBlank(userAgent)) {
            String errorMessage = "User-Agent为空！";
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }
        if (userAgent.contains("Alipay")) {
            order.setPayType(PayType.ALIPAY);
        } else if (userAgent.contains("wx")) {
            order.setPayType(PayType.WEPAY);
        }
        order.setPayType(PayType.WEPAY);
        order.setOrderStatus(OrderStatus.CREATED);
        // else {
        // String errorMessage = "请用微信或支付宝扫码";
        // model.put("order", order);
        // model.put("result", "failed");
        // model.put("resMsg", errorMessage);
        // return view;
        // }
        model.put("order", order);
        return view;
    }

}
