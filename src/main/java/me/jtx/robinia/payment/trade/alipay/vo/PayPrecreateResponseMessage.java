package me.jtx.robinia.payment.trade.alipay.vo;

public class PayPrecreateResponseMessage {
    private String tradeNo;

    private String barcodeFilePath;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getBarcodeFilePath() {
        return barcodeFilePath;
    }

    public void setBarcodeFilePath(String barcodeFilePath) {
        this.barcodeFilePath = barcodeFilePath;
    }
}
