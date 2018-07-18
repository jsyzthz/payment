package me.jtx.robinia.payment.trade.alipay.vo;

public class WapPayResponseMessage {
    private String tradeNo;
    private String payUrl;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
