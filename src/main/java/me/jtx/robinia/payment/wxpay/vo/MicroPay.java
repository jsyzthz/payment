package me.jtx.robinia.payment.wxpay.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * @author huazhong
 * @date 2018/07/19
 */
public class MicroPay {
	@NotNull
	private String description;
	@NotNull
	private String outTradeNo;
	private String deviceInfo;
	private String detail;
	private String attach;
	private String feeType;
	@NotNull
	private BigDecimal totalFee;
	@NotNull
	private String authCode;

	public MicroPay() {
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

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "MicroPay [description=" + description + ", outTradeNo=" + outTradeNo + ", deviceInfo=" + deviceInfo
				+ ", detail=" + detail + ", attach=" + attach + ", feeType=" + feeType + ", totalFee=" + totalFee
				+ ", authCode=" + authCode + "]";
	}

}
