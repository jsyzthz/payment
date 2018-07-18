package me.jtx.robinia.payment.wxpay.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huazhong
 * @date 2018/07/09
 */
public class MicropayPayVO {
    private String tradeNo;
    private String body;
    // 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
    private String deviceInfo;
    private String feeType;
    private String totalFee;
    // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
    private String spbillCreateIp;
    private String authCode;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Map<String, String> getParams() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", body);
        data.put("out_trade_no", tradeNo);
        data.put("device_info", deviceInfo);
        data.put("fee_type", feeType);
        data.put("total_fee", totalFee);
        data.put("spbill_create_ip", spbillCreateIp);
        data.put("auth_code", authCode);
        return data;
    }
}
