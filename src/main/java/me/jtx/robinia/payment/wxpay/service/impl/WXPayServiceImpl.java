/**
 * 
 */
package me.jtx.robinia.payment.wxpay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.jtx.robinia.payment.model.RobiniaPayResponse;
import me.jtx.robinia.payment.trade.wxpay.config.WXPayConfigImpl;
import me.jtx.robinia.payment.wxpay.sdk.WXPay;
import me.jtx.robinia.payment.wxpay.service.IWXPayService;
import me.jtx.robinia.payment.wxpay.vo.WXPayBill;
import me.jtx.robinia.payment.wxpay.vo.WXPayCloseOrder;
import me.jtx.robinia.payment.wxpay.vo.WXPayOrder;

/**
 * @author Huazhong
 *
 */
@Service
public class WXPayServiceImpl implements IWXPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WXPayServiceImpl.class);

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#payType1()
     */
    @Override
    public void payType1() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#payType2()
     */
    @Override
    public RobiniaPayResponse payType2(Map<String, String> data) throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);
        String outTradeNo = (String)data.get("out_trade_no");
        RobiniaPayResponse payResponse = new RobiniaPayResponse();
        Map<String, String> resp = wxpay.unifiedOrder(data);
        System.out.println(resp);
        String returnCode = (String)resp.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            String resultCode = (String)resp.get("result_code");
            if ("SUCCESS".equals(resultCode)) {
                LOGGER.info("订单号：{}生成微信支付码成功", outTradeNo);
                String urlCode = (String)resp.get("code_url");
                payResponse.setPayUrl(urlCode);
                // ConfigUtil.shorturl(urlCode);//转换为短链接
                // ZxingUtils.getQRCodeImge(urlCode, 256, imgPath);// 生成二维码
                LOGGER.info(urlCode);
            } else {
                String errCodeDes = (String)resp.get("err_code_des");
                LOGGER.info("订单号：{}生成微信支付码(系统)失败:{}", outTradeNo, errCodeDes);
                // message = Constants.FAIL;
            }
        } else {
            String returnMsg = (String)resp.get("return_msg");
            LOGGER.info("(订单号：{}生成微信支付码(通信)失败:{}", outTradeNo, returnMsg);
            // message = Constants.FAIL;
        }
        return payResponse;
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#payH5()
     */
    @Override
    public void payH5() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#orderQuery(java.lang.String)
     */
    @Override
    public WXPayOrder orderQuery(String outTradeNo) throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", outTradeNo);
        Map<String, String> resp = wxpay.orderQuery(data);
        return new WXPayOrder(resp);
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#refund()
     */
    @Override
    public String refund() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#refundQuery(java.lang.String)
     */
    @Override
    public String refundQuery(String outTradeNo) throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", outTradeNo);

        try {
            Map<String, String> resp = wxpay.refundQuery(data);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#closeOrder()
     */
    @Override
    public WXPayCloseOrder closeOrder(String outTradeNo) throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", outTradeNo);

        Map<String, String> resp = wxpay.closeOrder(data);
        return new WXPayCloseOrder(resp);
    }

    /* (non-Javadoc)
     * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#downlaodBill(java.util.Date, java.lang.String)
     */
    @Override
    public WXPayBill downlaodBill(Date billDate, String billType) throws Exception {
        LOGGER.info("下載下载对账单:{},{}", billDate, billType);
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("bill_date", DateFormatUtils.format(billDate, "yyyyMMdd"));
        data.put("bill_type", billType);
        // data.put("tar_type", "GZIP");

        Map<String, String> resp = wxpay.downloadBill(data);
        return new WXPayBill(resp);
    }

}
