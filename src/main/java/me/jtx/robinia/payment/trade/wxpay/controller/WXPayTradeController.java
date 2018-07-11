package me.jtx.robinia.payment.trade.wxpay.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.jtx.robinia.payment.trade.wxpay.config.WXPayConfigImpl;
import me.jtx.robinia.payment.trade.wxpay.sdk.WXPay;
import me.jtx.robinia.payment.trade.wxpay.vo.PayResponseMessage;
import me.jtx.robinia.payment.trade.wxpay.vo.PayVO;

@RestController
@RequestMapping("/v1/trade/wechat")
public class WXPayTradeController {

    @RequestMapping(value = "/pay", method = {RequestMethod.POST})
    public @ResponseBody PayResponseMessage tradePay(@ModelAttribute @Valid PayVO pay, BindingResult validResult) {
        PayResponseMessage responseMessage = new PayResponseMessage();
        responseMessage.setTradeNo(pay.getTradeNo());
        if (validResult.hasErrors()) {
//            responseMessage.setErrorMessage("Error!");
            return responseMessage;
        }

        try {
            WXPay wxpay = new WXPay(WXPayConfigImpl.getInstance());
            Map<String, String> resp = wxpay.microPay(pay.getParams());
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    @RequestMapping(value = "/pay2", method = {RequestMethod.POST})
    public @ResponseBody PayResponseMessage tradePay2(@ModelAttribute @Valid PayVO pay, BindingResult validResult) {
        PayResponseMessage responseMessage = new PayResponseMessage();
        responseMessage.setTradeNo(pay.getTradeNo());
        if (validResult.hasErrors()) {
//            responseMessage.setErrorMessage("Error!");
            return responseMessage;
        }

        try {
            WXPay wxpay = new WXPay(WXPayConfigImpl.getInstance());
            Map<String, String> resp = wxpay.unifiedOrder(pay.getParams());
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    @RequestMapping(value = "/{tradeNo}", method = {RequestMethod.GET})
    public @ResponseBody void tradePay(@PathVariable String tradeNo) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", tradeNo);

        try {
            WXPay wxpay = new WXPay(WXPayConfigImpl.getInstance());
            Map<String, String> resp = wxpay.orderQuery(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
