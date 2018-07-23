package me.jtx.robinia.payment.unionpay.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.jtx.robinia.payment.model.Goods;
import me.jtx.robinia.payment.model.PayWay;
import me.jtx.robinia.payment.unionpay.acp.sdk.AcpService;
import me.jtx.robinia.payment.unionpay.acp.sdk.SDKConstants;
import me.jtx.robinia.payment.unionpay.service.IUnionPayService;

@Controller
@RequestMapping("/v1/trade/unionpay")
public class UnionPayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnionPayController.class);

    @Autowired
    private IUnionPayService unionPayService;

    // @ApiOperation(value="银联支付主页")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "views/unionpay/index";
    }

    // @ApiOperation(value="电脑支付")
    @RequestMapping(value = "pcPay", method = RequestMethod.POST)
    public String pcPay(Goods goods, ModelMap map) {
        LOGGER.info("电脑支付");
        goods.setPayWay(PayWay.PC.getCode());
        String form = unionPayService.pay(goods);
        map.addAttribute("form", form);
        return "views/unionpay/pay";
    }

    // @ApiOperation(value="手机H5支付")
    @RequestMapping(value = "mobilePay", method = RequestMethod.POST)
    public String mobilePay(Goods goods, ModelMap map) {
        LOGGER.info("手机H5支付");
        goods.setPayWay(PayWay.MOBILE.getCode());
        String form = unionPayService.pay(goods);
        map.addAttribute("form", form);
        return "views/unionpay/pay";
    }

    // @ApiOperation(value="银联回调通知")
    @RequestMapping(value = "notify", method = RequestMethod.POST)
    public void union_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("银联接收后台通知开始");
        String encoding = request.getParameter(SDKConstants.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(request);
        // 打印参数
        LOGGER.info(reqParam.toString());
        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Entry<String, String> e = it.next();
                String key = (String)e.getKey();
                String value = (String)e.getValue();
                value = new String(value.getBytes(encoding), encoding);
                valideData.put(key, value);
            }
        }
        // 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            LOGGER.info("银联验证签名结果[失败].");
        } else {
            LOGGER.info("银联验证签名结果[成功].");
            String outtradeno = valideData.get("orderId");// 订单号
            String reqReserved = valideData.get("reqReserved");// 辅助信息(字段穿透)
            LOGGER.info("处理相关业务逻辑{},{}", outtradeno, reqReserved);
            response.getWriter().print("ok");// 返回给银联服务器http 200 状态码
        }
    }

    /**
     * 获取请求参数中所有的信息
     * 
     * @Author 科帮网
     * @param request
     * @return Map<String,String>
     * @Date 2017年8月2日 更新日志 2017年8月2日 科帮网 首次创建
     *
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String)temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                // System.out.println("ServletUtil类247行 temp数据的键=="+en+" 值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
