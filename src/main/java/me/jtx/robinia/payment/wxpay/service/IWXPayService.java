package me.jtx.robinia.payment.wxpay.service;

import java.util.Date;
import java.util.Map;

import me.jtx.robinia.payment.model.RobiniaPayResponse;
import me.jtx.robinia.payment.wxpay.vo.MicroPayResponse;
import me.jtx.robinia.payment.wxpay.vo.WXPayBill;
import me.jtx.robinia.payment.wxpay.vo.WXPayCloseOrder;
import me.jtx.robinia.payment.wxpay.vo.WXPayOrder;

/**
 * @author huazhong
 * @date 2018/07/18
 */
public interface IWXPayService {
	
	MicroPayResponse microPay(Map<String, String> data) throws Exception;

    void payType1();

    RobiniaPayResponse payType2(Map<String, String> data) throws Exception;

    void payH5();

    WXPayOrder orderQuery(String outTradeNo) throws Exception;

    String refund();

    String refundQuery(String outTradeNo) throws Exception;

    WXPayCloseOrder closeOrder(String outTradeNo) throws Exception;

    WXPayBill downlaodBill(Date billDate, String billType) throws Exception;
}
