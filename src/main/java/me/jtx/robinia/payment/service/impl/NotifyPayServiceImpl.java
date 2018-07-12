package me.jtx.robinia.payment.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.jtx.robinia.payment.service.IPayNotifyService;

/**
 * @author huazhong
 * @date 2018/07/12
 */
public class NotifyPayServiceImpl implements IPayNotifyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyPayServiceImpl.class);

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.service.IPayNotifyService#doAliPayNotify(java.lang.String)
     */
    @Override
    public Map doAliPayNotify(String jsonParam) {
        return null;
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.service.IPayNotifyService#doWePayNotify(java.lang.String)
     */
    @Override
    public Map doWePayNotify(String jsonParam) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.service.IPayNotifyService#sendBizPayNotify(java.lang.String)
     */
    @Override
    public Map sendBizPayNotify(String jsonParam) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.service.IPayNotifyService#handleAliPayNotify(java.util.Map)
     */
    @Override
    public String handleAliPayNotify(Map params) {
        return null;
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.service.IPayNotifyService#handleWxPayNotify(java.lang.String)
     */
    @Override
    public String handleWxPayNotify(String xmlResult) {
        // TODO Auto-generated method stub
        return null;
    }

}
