package me.jtx.robinia.payment.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.jtx.robinia.payment.model.AlipayNotify;

@RestController
@RequestMapping("/v1")
public class NotifyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyController.class);

    @RequestMapping(value = "/alipay/notify")
    @ResponseBody
    public String aliPayNotify(AlipayNotify alipayNotify) throws ServletException, IOException {
        LOGGER.info("====== 开始接收支付宝支付回调通知 ======");
//        Map<String, String> params = new HashMap<String, String>();
//        Map<String, String[]> requestParams = request.getParameterMap();
//        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = iter.next();
//            String[] values = requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//            }
//            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//            params.put(name, valueStr);
//        }
//        LOGGER.info("通知请求数据:reqStr={}", params);
//        if (params.isEmpty()) {
//            LOGGER.error("请求参数为空");
//            return "fail";
//        }
        String notifyResponse = null;// notifyPayService.handleAliPayNotify(params);
        LOGGER.info("响应给支付宝:{}", notifyResponse);
        LOGGER.info("====== 完成接收支付宝支付回调通知 ======");
        return notifyResponse;
    }
}
