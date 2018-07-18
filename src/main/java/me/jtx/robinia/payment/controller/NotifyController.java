package me.jtx.robinia.payment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    @RequestMapping(value = "/wechat/notify")
    @ResponseBody
	public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	InputStream inputStream = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();
		LOGGER.debug(sb.toString());
		System.out.println(sb.toString());
    }
}
