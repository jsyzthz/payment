package me.jtx.robinia.payment.wxpay.vo;
//https://gitee.com/52itstyle/spring-boot-pay/blob/master/src/main/java/com/itstyle/modules/weixinpay/controller/WeixinPayController.java
//https://blog.csdn.net/dmw412724/article/details/75106254
public class WXPayNotify {
    private String appid;
    private String openid;
    private String mch_id;
    private String is_subscribe;
    private String monce_str;
    
    private String attach;
    private String bankType;
    private String cash_fee;
    private String out_trade_no;
    private String result_code;
    private String return_code;
    private String sign;
    private String time_end;
    private String total_fee;
    private String trade_type;
    private String transcation_id;
}
