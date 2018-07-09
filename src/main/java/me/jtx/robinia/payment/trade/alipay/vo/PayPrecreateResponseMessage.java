package me.jtx.robinia.payment.trade.alipay.vo;

import me.jtx.robinia.payment.trade.StatusResponseMessage;

public class PayPrecreateResponseMessage extends StatusResponseMessage {
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

    public void setErrorMessage(String errorMessage) {
        super.setMessage(errorMessage);
        super.setStatus(false);
    }
}
