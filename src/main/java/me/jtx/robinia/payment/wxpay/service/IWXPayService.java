package me.jtx.robinia.payment.wxpay.service;

/**
 * @author huazhong
 * @date 2018/07/18
 */
public interface IWXPayService {

    void payType1();

    void payType2();

    void payH5();

    String refund();

    String closeOrder();

    void downlaodBill();
}
