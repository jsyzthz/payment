 package me.jtx.robinia.payment.model;

import java.util.Date;

public class AlipayNotify {
     private Date notifyTime;
     private String notifyType;
     private String notifyId;
     private String signType;
     private String sign;
     private String tradeNo;
     private String appId;
     private String outTradeNo;
     private String outBizNo;
     private String buyerId;
     private String buyerLogonId;
     private String sellerId;
     private String sellerEmail;
     private String tradeStatus;
     private Double totalAmount;
     private Double receiptAmount;
     private Double invoiceAmount;
     private Double buyerPayAmount;
     private Double pointAmount;
     private Double refundFee;
     private Double sendBackFee;
     private String subject;
     private String body;
     private Date gmtCreate;
     private Date gmtPayment;
     private Date gmtRefund;
     private Date gmtClose;
     private String fundBillList;
}
