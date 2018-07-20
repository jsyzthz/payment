package me.jtx.robinia.payment.wxpay.vo;

import java.util.Map;

import me.jtx.robinia.payment.model.State;

/**
 * @author huazhong
 * @date 2018/07/19
 */
public class H5PayResponse {
    private State returnState;
    private String returnMsg;
    private String appId;
    private String mchId;
    private String nonceStr;
    private String sign;
    private State resultState;
    private String errCode;
    private String errCodeDes;
    private String deviceInfo;

    private String prepayId;
    private String tradeType;
    private String mweburl;

    public H5PayResponse(Map<String, String> data) {
        String returnCode = data.get("return_code");

        if ("SUCCESS".equals(returnCode)) {
            this.returnState = State.SUCCESS;
            this.appId = data.get("appid");
            this.mchId = data.get("mch_id");
            this.nonceStr = data.get("nonce_str");
            this.sign = data.get("sign");
            this.deviceInfo = data.get("device_info");
            String resultCode = data.get("result_code");

            if ("SUCCESS".equals(resultCode)) {
                this.resultState = State.SUCCESS;
                this.prepayId = data.get("prepay_id");
                this.tradeType = data.get("trade_type");
                this.mweburl = data.get("mweb_url");
            } else {
                this.resultState = State.FAIL;
                this.errCode = data.get("err_code");
                this.errCodeDes = data.get("err_code_des");
            }
        } else {
            this.returnState = State.FAIL;
        }
        this.returnMsg = data.get("return_msg");
    }

    public State getReturnState() {
        return returnState;
    }

    public void setReturnState(State returnState) {
        this.returnState = returnState;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public State getResultState() {
        return resultState;
    }

    public void setResultState(State resultState) {
        this.resultState = resultState;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getMweburl() {
        return mweburl;
    }

    public void setMweburl(String mweburl) {
        this.mweburl = mweburl;
    }
}
