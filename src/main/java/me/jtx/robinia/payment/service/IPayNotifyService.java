package me.jtx.robinia.payment.service;

/**
 * @author huazhong
 * @date 2018/07/12
 */
public interface IPayNotifyService {

	boolean handlePayNotify(String notifyData) throws Exception ;
}
