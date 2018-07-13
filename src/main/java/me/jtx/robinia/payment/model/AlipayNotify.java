package me.jtx.robinia.payment.model;

import java.util.Date;
public class AlipayNotify {
//    @ParamName("notify_time")
    private Date notifyTime;

//    @ParamName("notify_type")
    private String notifyType;

//    @ParamName("notify_id")
    private String notifyId;

//    @ParamName("sign_type")
    private String signType;

//    @ParamName("sign")
    private String sign;

//    @ParamName("trade_no")
    private String tradeNo;

//    @ParamName("app_id")
    private String appId;

//    @ParamName("out_trade_no")
    private String outTradeNo;

//    @ParamName("out_biz_no")
    private String outBizNo;

//    @ParamName("buyer_id")
    private String buyerId;

//    @ParamName("buyer_logon_id")
    private String buyerLogonId;

//    @ParamName("seller_id")
    private String sellerId;

//    @ParamName("seller_email")
    private String sellerEmail;

//    @ParamName("trade_status")
    private String tradeStatus;

//    @ParamName("total_amount")
    private Double totalAmount;

//    @ParamName("receipt_amount")
    private Double receiptAmount;

//    @ParamName("invoice_amount")
    private Double invoiceAmount;

//    @ParamName("buyer_pay_amount")
    private Double buyerPayAmount;

//    @ParamName("point_amount")
    private Double pointAmount;

//    @ParamName("refund_fee")
    private Double refundFee;

//    @ParamName("send_back_fee")
    private Double sendBackFee;

//    @ParamName("subject")
    private String subject;

//    @ParamName("body")
    private String body;

//    @ParamName("gmt_create")
    private Date gmtCreate;

//    @ParamName("gmt_payment")
    private Date gmtPayment;

//    @ParamName("gmt_refund")
    private Date gmtRefund;

//    @ParamName("gmt_close")
    private Date gmtClose;

//    @ParamName("fund_bill_list")
    private String fundBillList;

    public Date getNotifyTime() {
        return notifyTime;
    }

//    public void setNotify_time(Date notifyTime) {
//        this.notifyTime = notifyTime;
//    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotify_type(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotify_id(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSign_type(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTrade_no(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setApp_id(String appId) {
        this.appId = appId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOut_trade_no(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOut_biz_no(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyer_id(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyer_logon_id(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSeller_id(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSeller_email(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTrade_status(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotal_amount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceipt_amount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoice_amount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyer_pay_amount(Double buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public Double getPointAmount() {
        return pointAmount;
    }

    public void setPoint_amount(Double pointAmount) {
        this.pointAmount = pointAmount;
    }

    public Double getRefundFee() {
        return refundFee;
    }

    public void setRefund_fee(Double refundFee) {
        this.refundFee = refundFee;
    }

    public Double getSendBackFee() {
        return sendBackFee;
    }

    public void setSend_back_fee(Double sendBackFee) {
        this.sendBackFee = sendBackFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

//    public void setGmt_create(Date gmtCreate) {
//        this.gmtCreate = gmtCreate;
//    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

//    public void setGmt_payment(Date gmtPayment) {
//        this.gmtPayment = gmtPayment;
//    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

//    public void setGmt_refund(Date gmtRefund) {
//        this.gmtRefund = gmtRefund;
//    }

    public Date getGmtClose() {
        return gmtClose;
    }

//    public void setGmt_close(Date gmtClose) {
//        this.gmtClose = gmtClose;
//    }

    public String getFundBillList() {
        return fundBillList;
    }

    public void setFund_bill_list(String fundBillList) {
        this.fundBillList = fundBillList;
    }
}
