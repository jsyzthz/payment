package me.jtx.robinia.payment.service;

import java.util.Map;

/**
 * @author huazhong
 * @date 2018/07/12
 */
public interface IPayNotifyService {

    Map doAliPayNotify(String jsonParam);

    Map doWePayNotify(String jsonParam);

    Map sendBizPayNotify(String jsonParam);

    String handleAliPayNotify(Map params);

    String handleWxPayNotify(String xmlResult);
}
