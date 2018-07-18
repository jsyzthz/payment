package me.jtx.robinia.payment.wxpay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.jtx.robinia.payment.service.IPayNotifyService;
import me.jtx.robinia.payment.trade.wxpay.config.WXPayConfigImpl;
import me.jtx.robinia.payment.wxpay.sdk.WXPay;
import me.jtx.robinia.payment.wxpay.service.IWXPayService;
import me.jtx.robinia.payment.wxpay.vo.PayResponseMessage;
import me.jtx.robinia.payment.wxpay.vo.PayVO;
import me.jtx.robinia.payment.wxpay.vo.WXPayNotifyResponse;

@RestController
@RequestMapping("/v1/trade/wechat")
public class WXPayTradeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WXPayTradeController.class);

	@Autowired
	private IWXPayService payService;

	@Autowired
	@Qualifier("notifyWXPayService")
	private IPayNotifyService notifyPayService;

	@RequestMapping(value = "/pay", method = { RequestMethod.POST })
	public @ResponseBody PayResponseMessage tradePay(@ModelAttribute @Valid PayVO pay, BindingResult validResult) {
		PayResponseMessage responseMessage = new PayResponseMessage();
		responseMessage.setTradeNo(pay.getTradeNo());
		if (validResult.hasErrors()) {
			// responseMessage.setErrorMessage("Error!");
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

	@RequestMapping(value = "/pay2", method = { RequestMethod.POST })
	public @ResponseBody PayResponseMessage tradePay2(@ModelAttribute @Valid PayVO pay, BindingResult validResult) {
		PayResponseMessage responseMessage = new PayResponseMessage();
		responseMessage.setTradeNo(pay.getTradeNo());
		if (validResult.hasErrors()) {
			// responseMessage.setErrorMessage("Error!");
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

	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public @ResponseBody void test() {
		try {
			payService.payType2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{tradeNo}", method = { RequestMethod.GET })
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

	@RequestMapping(value = "/notify", produces = { "application/xml;charset=UTF-8" })
	public @ResponseBody WXPayNotifyResponse notify(HttpServletRequest request) {
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
