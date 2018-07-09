package me.jtx.robinia.payment.trade.alipay.vo;

import javax.validation.constraints.NotNull;

import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradeRefundRequestBuilder;

public class PayRefundVO {
    @NotNull(message = "商户订单号不能为空")
    private String tradeNo;

    @NotNull(message = "退款金额不能为空")
    private String refundAmount;

    private String outRequestNo;

    @NotNull(message = "退款原因不能为空")
    private String refundReason;

    @NotNull(message = "商户门店编号不能为空")
    private String storeId;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public AlipayTradeRefundRequestBuilder getBuilder() {
        AlipayTradeRefundRequestBuilder builder =
            new AlipayTradeRefundRequestBuilder().setOutTradeNo(tradeNo).setRefundAmount(refundAmount)
                .setRefundReason(refundReason).setOutRequestNo(outRequestNo).setStoreId(storeId);
        return builder;
    }
}
