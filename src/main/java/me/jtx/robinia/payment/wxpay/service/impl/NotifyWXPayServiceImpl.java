/**
 * 
 */
package me.jtx.robinia.payment.wxpay.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.jtx.robinia.payment.service.impl.NotifyPayServiceImpl;
import me.jtx.robinia.payment.trade.wxpay.config.WXPayConfigImpl;
import me.jtx.robinia.payment.wxpay.sdk.WXPay;
import me.jtx.robinia.payment.wxpay.sdk.WXPayUtil;

/**
 * @author Huazhong
 *
 */
@Service("notifyWXPayService")
public class NotifyWXPayServiceImpl extends NotifyPayServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(NotifyWXPayServiceImpl.class);

	@Override
	public boolean handlePayNotify(String notifyData) throws Exception {
		LOGGER.info("微信支付回调处理中。。。");
		WXPayConfigImpl config = WXPayConfigImpl.getInstance();
		WXPay wxpay = new WXPay(config);

		Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData); // 转换成map
		if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
			LOGGER.info("微信支付回调签名验证成功.");
			if ("SUCCESS".equals((String) notifyMap.get("return_code"))
					&& "SUCCESS".equals((String) notifyMap.get("result_code"))) {
				String orderNo = (String) notifyMap.get("out_trade_no");
				LOGGER.info("微信订单号{}付款成功", orderNo);
				return true;
			}
		} else {
			// 签名错误，如果数据里没有sign字段，也认为是签名错误
			LOGGER.info("微信支付回调签名错误.");
		}
		return false;
	}

}
