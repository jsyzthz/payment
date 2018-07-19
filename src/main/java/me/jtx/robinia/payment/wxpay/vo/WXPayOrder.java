package me.jtx.robinia.payment.wxpay.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.jtx.robinia.payment.model.State;
import me.jtx.robinia.payment.model.TradeState;

public class WXPayOrder {
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
    private String openId;
    private boolean subscribe;
    private String tradeType;
    private TradeState tradeState;
    private String bankType;
    private BigDecimal totalFee;
    private BigDecimal settlementTotalFee;
    private String feeType;
    private BigDecimal cashFee;
    private String cashFeeType;
    private BigDecimal couponFee;
    private Integer couponCount;
    private List<Coupon> coupons;
    private String transactionId;
    private String outTradeNo;
    private String attach;
    private String timeEnd;
    private String tradeStateDesc;

    public WXPayOrder(Map<String, String> data) {
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
                String tradeState = data.get("trade_state");
                this.tradeState = TradeState.valueOf(tradeState);
                this.tradeStateDesc = data.get("trade_state_desc");
                this.outTradeNo = data.get("out_trade_no");
                this.attach = data.get("attach");

                if ("SUCCESS".equals(tradeState)) {
                    this.deviceInfo = data.get("device_info");
                    this.openId = data.get("openid");
                    this.subscribe = data.get("is_subscribe").equalsIgnoreCase("Y");
                    this.tradeType = data.get("trade_type");
                    this.bankType = data.get("bank_type");
                    this.totalFee = BigDecimal.valueOf(Long.valueOf(data.get("total_fee"))).divide(new BigDecimal(100));
                    String settlementTotalFeeStr = data.get("settlement_total_fee");
                    if (settlementTotalFeeStr != null) {
                        this.settlementTotalFee =
                            BigDecimal.valueOf(Long.valueOf(settlementTotalFeeStr)).divide(new BigDecimal(100));
                    }

                    this.feeType = data.get("fee_type");
                    this.cashFee = BigDecimal.valueOf(Long.valueOf(data.get("cash_fee"))).divide(new BigDecimal(100));
                    this.cashFeeType = data.get("cash_fee_type");

                    String couponFeeStr = data.get("coupon_fee");
                    if (couponFeeStr != null) {
                        this.couponFee = BigDecimal.valueOf(Long.valueOf(couponFeeStr)).divide(new BigDecimal(100));
                    }
                    String couponCountStr = data.get("coupon_count");
                    if (couponCountStr != null) {
                        this.couponCount = Integer.parseInt(data.get("coupon_count"));
                    }
                    for (int i = 0, size = data.size(); i < size; i++) {
                        String couponId = data.get("coupon_id_" + i);
                        String couponType = data.get("coupon_type_" + i);
                        String couponFee = data.get("coupon_fee_" + i);
                        if (couponId == null && couponType == null && couponFee == null) {
                            break;
                        }
                        if (coupons == null) {
                            coupons = new ArrayList<Coupon>();
                            Coupon coupon = new Coupon(couponId, couponType, couponFee);
                            coupons.add(coupon);
                        }
                    }
                    this.transactionId = data.get("transaction_id");
                    this.timeEnd = data.get("time_end");
                }
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public TradeState getTradeState() {
        return tradeState;
    }

    public void setTradeState(TradeState tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(BigDecimal settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public BigDecimal getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(BigDecimal couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}
