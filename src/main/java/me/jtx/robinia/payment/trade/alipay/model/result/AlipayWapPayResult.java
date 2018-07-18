package me.jtx.robinia.payment.trade.alipay.model.result;

import com.alipay.api.response.AlipayTradeWapPayResponse;

import me.jtx.robinia.payment.trade.alipay.model.TradeStatus;

/**
 * Created by liuyangkly on 15/8/26.
 */
public class AlipayWapPayResult implements Result {
    private TradeStatus tradeStatus;
    private AlipayTradeWapPayResponse response;

    public AlipayWapPayResult(AlipayTradeWapPayResponse response) {
        this.response = response;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public void setResponse(AlipayTradeWapPayResponse response) {
        this.response = response;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public AlipayTradeWapPayResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
