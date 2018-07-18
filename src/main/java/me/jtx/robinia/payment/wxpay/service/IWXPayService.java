package me.jtx.robinia.payment.wxpay.service;

import java.util.Date;

/**
 * @author huazhong
 * @date 2018/07/18
 */
public interface IWXPayService {

    void payType1();

    void payType2() throws Exception;

    void payH5();
    
    String orderQuery(String outTradeNo);

    String refund();
    
    String refundQuery(String outTradeNo) throws Exception;

    String closeOrder();

    void downlaodBill(Date billDate, String billType);
}
