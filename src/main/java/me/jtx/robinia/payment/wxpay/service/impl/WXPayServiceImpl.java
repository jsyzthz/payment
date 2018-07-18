/**
 * 
 */
package me.jtx.robinia.payment.wxpay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.jtx.robinia.payment.trade.wxpay.config.WXPayConfigImpl;
import me.jtx.robinia.payment.wxpay.sdk.WXPay;
import me.jtx.robinia.payment.wxpay.service.IWXPayService;

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
	public void payType2() throws Exception {
		WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值1");
        data.put("out_trade_no", "RWXT201807281059590013");
        data.put("device_info", "Computer1");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "112.87.181.223");
        data.put("notify_url", "http://robina.free.ngrok.cc/v1/trade/wechat/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            String returnCode = (String) resp.get("return_code");
            if("SUCCESS".equals(returnCode)){
    			String resultCode = (String) resp.get("result_code");
    			if("SUCCESS".equals(resultCode)){
    				LOGGER.info("订单号：{}生成微信支付码成功","RWXT2018072810595900000012");
    				String urlCode = (String) resp.get("code_url");
//    				ConfigUtil.shorturl(urlCode);//转换为短链接
//    				ZxingUtils.getQRCodeImge(urlCode, 256, imgPath);// 生成二维码
    				LOGGER.info(urlCode);
    			}else{
    				String errCodeDes = (String) resp.get("err_code_des");
    				LOGGER.info("订单号：{}生成微信支付码(系统)失败:{}","RWXT2018072810595900000012",errCodeDes);
//    				message = Constants.FAIL;
    			}
    		}else{
    			String returnMsg = (String) resp.get("return_msg");
    			LOGGER.info("(订单号：{}生成微信支付码(通信)失败:{}","RWXT2018072810595900000012",returnMsg);
//    			message = Constants.FAIL;
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
	public String orderQuery(String outTradeNo) {
		// TODO Auto-generated method stub
		return null;
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
	public String closeOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see me.jtx.robinia.payment.wxpay.service.IWXPayService#downlaodBill(java.util.Date, java.lang.String)
	 */
	@Override
	public void downlaodBill(Date billDate, String billType) {
		// TODO Auto-generated method stub

	}

}
