package me.jtx.robinia.payment.wxpay.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="xml")
public class WXPayNotifyResponse {
	@JacksonXmlProperty(localName="return_code")
	private String returnCode;
	@JacksonXmlProperty(localName="return_msg")
	private String returnMessage;

	public WXPayNotifyResponse() {
		super();
	}

	public WXPayNotifyResponse(String returnCode, String returnMessage) {
		super();
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

}
