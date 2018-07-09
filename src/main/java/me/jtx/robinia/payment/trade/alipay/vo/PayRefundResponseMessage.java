package me.jtx.robinia.payment.trade.alipay.vo;

import me.jtx.robinia.payment.trade.StatusResponseMessage;

public class PayRefundResponseMessage extends StatusResponseMessage {
    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public void setErrorMessage(String errorMessage) {
        super.setMessage(errorMessage);
        super.setStatus(false);
    }
}
