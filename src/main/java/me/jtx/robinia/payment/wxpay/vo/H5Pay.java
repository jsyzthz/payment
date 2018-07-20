package me.jtx.robinia.payment.wxpay.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * @author huazhong
 * @date 2018/07/19
 */
public class H5Pay {
    @NotNull
    private String description;
    @NotNull
    private String outTradeNo;
    private String deviceInfo;
    private String feeType;
    @NotNull
    private BigDecimal totalFee;
    private String productId;
    private String detail;
    private String attach;
    @NotNull
    private String sceneInfo;

    public H5Pay() {
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

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getTotalFeeCents() {
        BigDecimal fenBd = totalFee.multiply(new BigDecimal(100));
        fenBd = fenBd.setScale(0, BigDecimal.ROUND_HALF_UP);
        return fenBd;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    @Override
    public String toString() {
        return "H5Pay [description=" + description + ", outTradeNo=" + outTradeNo + ", deviceInfo=" + deviceInfo
            + ", feeType=" + feeType + ", totalFee=" + totalFee + ", productId=" + productId + ", detail=" + detail
            + ", attach=" + attach + ", sceneInfo=" + sceneInfo + "]";
    }
}
