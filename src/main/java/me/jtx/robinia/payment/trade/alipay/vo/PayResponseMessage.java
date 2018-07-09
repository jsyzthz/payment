package me.jtx.robinia.payment.trade.alipay.vo;

public class PayResponseMessage extends StatusResponseMessage {
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
