package me.jtx.robinia.payment.wxpay.vo;

import java.util.Map;

import me.jtx.robinia.payment.model.State;

public class WXPayBill {
    private State returnState;
    private String returnMsg;
    private String data;

    public WXPayBill(Map<String, String> data) {
        String returnCode = data.get("return_code");

        if ("SUCCESS".equals(returnCode)) {
            this.returnState = State.SUCCESS;
            this.data=data.get("data");
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
