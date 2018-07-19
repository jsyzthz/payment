package me.jtx.robinia.payment.wxpay.controller;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.jtx.robinia.payment.model.RobiniaPayResponse;
import me.jtx.robinia.payment.service.IPayNotifyService;
import me.jtx.robinia.payment.trade.Result;
import me.jtx.robinia.payment.trade.wxpay.config.WXPayConfigImpl;
import me.jtx.robinia.payment.wxpay.sdk.WXPay;
import me.jtx.robinia.payment.wxpay.service.IWXPayService;
import me.jtx.robinia.payment.wxpay.vo.MicroPay;
import me.jtx.robinia.payment.wxpay.vo.MicroPayResponse;
import me.jtx.robinia.payment.wxpay.vo.PayResponseMessage;
import me.jtx.robinia.payment.wxpay.vo.PayVO;
import me.jtx.robinia.payment.wxpay.vo.WXPayBill;
import me.jtx.robinia.payment.wxpay.vo.WXPayCloseOrder;
import me.jtx.robinia.payment.wxpay.vo.WXPayNotifyResponse;
import me.jtx.robinia.payment.wxpay.vo.WXPayOrder;
import me.jtx.robinia.payment.wxpay.vo.WePayType2;

@RestController
@RequestMapping("/v1/trade/wechat")
public class WXPayTradeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WXPayTradeController.class);

	@Autowired
	private IWXPayService payService;

	@Autowired
	@Qualifier("notifyWXPayService")
	private IPayNotifyService notifyPayService;

	@RequestMapping(value = "/micropay", method = { RequestMethod.POST })
	public @ResponseBody Result<MicroPayResponse> microPay(@Valid @RequestBody MicroPay microPay) {
		LOGGER.debug("微信刷卡支付: {}.", microPay);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", microPay.getDescription());
		data.put("out_trade_no", microPay.getOutTradeNo());
		data.put("device_info", microPay.getDeviceInfo());
		data.put("fee_type", microPay.getFeeType());
		data.put("total_fee", microPay.getTotalFeeCents().toPlainString());
		data.put("spbill_create_ip", "112.87.181.223");
		data.put("auth_code", microPay.getAuthCode());
		if (StringUtils.isNotEmpty(microPay.getAttach())) {
			data.put("attach", microPay.getAttach());
		}
		MicroPayResponse microPayResponse;
		try {
			microPayResponse=payService.microPay(data);
			return Result.createBySuccessResult("SUCCESS", microPayResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.createByErrorResult();
	}

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
	public @ResponseBody Result<RobiniaPayResponse> tradePay2(@Valid @RequestBody WePayType2 wePay) {
		LOGGER.debug("微信扫码支付模式二: {}.", wePay);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", wePay.getDescription());
		data.put("out_trade_no", wePay.getOutTradeNo());
		data.put("device_info", wePay.getDeviceInfo());
		data.put("fee_type", wePay.getFeeType());
		data.put("total_fee", wePay.getTotalFeeCents().toPlainString());
		data.put("spbill_create_ip", "112.87.181.223");
		data.put("notify_url", "http://robina.free.ngrok.cc/v1/trade/wechat/notify");
		data.put("trade_type", "NATIVE"); // 此处指定为扫码支付
		if (StringUtils.isNotEmpty(wePay.getProductId())) {
			data.put("product_id", wePay.getProductId());
		}
		RobiniaPayResponse payResponse;
		try {
			payResponse = payService.payType2(data);
			return Result.createBySuccessResult("SUCCESS", payResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Result.createByErrorResult();
	}

	@RequestMapping(value = "/{outTradeNo}", method = { RequestMethod.GET })
	public @ResponseBody Result<WXPayOrder> tradePay(@PathVariable String outTradeNo) {
		LOGGER.debug("微信查询订单{}.", outTradeNo);
		try {
			WXPayOrder order = payService.orderQuery(outTradeNo);
			return Result.createBySuccessResult("SUCCESS", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.createByErrorResult();
	}

	@RequestMapping(value = "/{outTradeNo}/actions/close", method = { RequestMethod.GET })
	public @ResponseBody Result<WXPayCloseOrder> close(@PathVariable String outTradeNo) {
		LOGGER.debug("微信关闭订单{}.", outTradeNo);
		try {
			WXPayCloseOrder closeOrder = payService.closeOrder(outTradeNo);
			return Result.createBySuccessResult("SUCCESS", closeOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.createByErrorResult();
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

	@RequestMapping(value = "/bill/{billDate}/{billType}/download")
	public @ResponseBody Result<WXPayBill> downloadBill(
			@PathVariable @DateTimeFormat(pattern = "yyyyMMdd") Date billDate, @PathVariable String billType) {

		try {
			WXPayBill bill = payService.downlaodBill(billDate, billType);
			return Result.createBySuccessResult("SUCCESS", bill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result.createByErrorResult();
	}
}
