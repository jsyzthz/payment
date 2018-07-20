package me.jtx.robinia.payment.wxpay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.jtx.robinia.payment.service.IPayNotifyService;
import me.jtx.robinia.payment.wxpay.service.IWXPayService;
import me.jtx.robinia.payment.wxpay.vo.H5Pay;
import me.jtx.robinia.payment.wxpay.vo.H5PayResponse;
import me.jtx.robinia.payment.wxpay.vo.WXPayNotifyResponse;

@Controller
@RequestMapping("/v1/trade/wechat/h5")
public class WXH5PayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WXH5PayController.class);

    @Autowired
    private IWXPayService payService;

    @Autowired
    @Qualifier("notifyWXPayService")
    private IPayNotifyService notifyPayService;

    @RequestMapping(value = "/pay", method = {RequestMethod.GET})
    public String h5Pay() {
        H5Pay h5Pay = new H5Pay();
        h5Pay.setDescription("H5支付测试");
        h5Pay.setOutTradeNo("RWXT201807281059590025");
        h5Pay.setTotalFee(new BigDecimal("0.01"));
        h5Pay.setDeviceInfo("Computer01");
        h5Pay.setFeeType("CNY");
        h5Pay.setProductId("15");
        h5Pay.setDetail("测试H5");
        h5Pay.setAttach("ncs");
        h5Pay.setSceneInfo(
            "{\"h5_info\": {\"type\":\"IOS\",\"app_name\": \"王者荣耀\",\"package_name\": \"com.tencent.tmgp.sgame\"}}");

        LOGGER.debug("H5支付: {}.", h5Pay);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", h5Pay.getDescription());
        data.put("out_trade_no", h5Pay.getOutTradeNo());
        data.put("total_fee", h5Pay.getTotalFeeCents().toPlainString());
        data.put("spbill_create_ip", "112.87.181.223");
        data.put("notify_url", "http://robina.free.ngrok.cc/v1/trade/wechat/notify");
        data.put("trade_type", "MWEB"); // 此处指定为扫码支付
        if (StringUtils.isNotEmpty(h5Pay.getProductId())) {
            data.put("product_id", h5Pay.getProductId());
        }
        if (StringUtils.isNotEmpty(h5Pay.getDeviceInfo())) {
            data.put("device_info", h5Pay.getDeviceInfo());
        }
        if (StringUtils.isNotEmpty(h5Pay.getFeeType())) {
            data.put("fee_type", h5Pay.getFeeType());
        }
        if (StringUtils.isNotEmpty(h5Pay.getAttach())) {
            data.put("attach", h5Pay.getAttach());
        }

        H5PayResponse H5payResponse;
        try {
            H5payResponse = payService.h5Pay(data);
            return "redirect:" + H5payResponse.getMweburl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @RequestMapping(value = "/pay/success")
    public @ResponseBody WXPayNotifyResponse paySuccess(HttpServletRequest request) {
        try {
            String notifyData = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

            LOGGER.debug("微信支付回调请求.{}", notifyData);
            boolean result = notifyPayService.handlePayNotify(notifyData);
            if (result) {
                return new WXPayNotifyResponse("SUCCESS", "OK");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WXPayNotifyResponse("FAIL", "报文为空");
    }
}
