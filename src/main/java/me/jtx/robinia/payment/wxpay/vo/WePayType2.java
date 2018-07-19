package me.jtx.robinia.payment.wxpay.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * @author huazhong
 * @date 2018/07/19
 */
public class WePayType2 {
    @NotNull
    private String description;
    @NotNull
    private String outTradeNo;
    private String deviceInfo;
    private String feeType;
    @NotNull
    private BigDecimal totalFee;
    private String productId;

    public WePayType2() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public BigDecimal getTotalFeeCents() {
    	BigDecimal fenBd = totalFee.multiply(new BigDecimal(100));
		fenBd = fenBd.setScale(0, BigDecimal.ROUND_HALF_UP);
		return fenBd;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "WePayType2 [description=" + description + ", outTradeNo=" + outTradeNo + ", deviceInfo=" + deviceInfo
            + ", feeType=" + feeType + ", totalFee=" + totalFee + ", productId=" + productId + "]";
    }
}
