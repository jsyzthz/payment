package me.jtx.robinia.payment.trade.alipay.config;

/**
 * Created by liuyangkly on 15/7/29.
 */
public class Constants {

    private Constants() {
        // No Constructor.
    }

    public static final String SUCCESS = "10000"; // 成功
    public static final String PAYING = "10003"; // 用户支付中
    public static final String FAILED = "40004"; // 失败
    public static final String ERROR = "20000"; // 系统异常

    public static final String TIMEOUT_EXPRESS = "5m";// 支付超时，线下扫码交易定义为5分钟
}
