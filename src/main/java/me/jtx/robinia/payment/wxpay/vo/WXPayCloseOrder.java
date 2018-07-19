package me.jtx.robinia.payment.wxpay.vo;

import java.util.Map;

import me.jtx.robinia.payment.model.State;

public class WXPayCloseOrder {
    private State returnState;
    private String returnMsg;
    private String appId;
    private String mchId;
    private String nonceStr;
    private String sign;
    private State resultState;
    private String errCode;
    private String errCodeDes;

    public WXPayCloseOrder(Map<String, String> data) {
        String returnCode = data.get("return_code");

        if ("SUCCESS".equals(returnCode)) {
            this.returnState = State.SUCCESS;
            this.appId = data.get("appid");
            this.mchId = data.get("mch_id");
            this.nonceStr = data.get("nonce_str");
            this.sign = data.get("sign");
            String resultCode = data.get("result_code");
            
            if ("SUCCESS".equals(resultCode)) {
                this.resultState = State.SUCCESS;
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
}
