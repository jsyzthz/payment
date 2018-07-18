package me.jtx.robinia.payment.trade.alipay.service;

import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradePayRequestBuilder;
import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradePrecreateRequestBuilder;
import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradeQueryRequestBuilder;
import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradeRefundRequestBuilder;
import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradeWapPayRequestBuilder;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FPayResult;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FPrecreateResult;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FQueryResult;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayF2FRefundResult;
import me.jtx.robinia.payment.trade.alipay.model.result.AlipayWapPayResult;

/**
 * Created by liuyangkly on 15/7/29.
 */
public interface AlipayTradeService {

    // 当面付2.0流程支付
    public AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder);

    // 当面付2.0消费查询
    public AlipayF2FQueryResult queryTradeResult(AlipayTradeQueryRequestBuilder builder);

    // 当面付2.0消费退款
    public AlipayF2FRefundResult tradeRefund(AlipayTradeRefundRequestBuilder builder);

    // 当面付2.0预下单(生成二维码)
    public AlipayF2FPrecreateResult tradePrecreate(AlipayTradePrecreateRequestBuilder builder);
    
    // 手机网站支付 2.0
    public AlipayWapPayResult tradeWapPay(AlipayTradeWapPayRequestBuilder builder);
}
