 package me.jtx.robinia.payment.unionpay.service;

import java.util.Map;

import me.jtx.robinia.payment.model.Goods;

/**
 * @author huazhong
 * @date 2018/07/23
 */
public interface IUnionPayService {
    /**
     * 银联支付
     * @Author  科帮网
     * @param product
     * @return  String
     * @Date    2017年8月2日
     * 更新日志
     * 2017年8月2日  科帮网 首次创建
     *
     */
    String pay(Goods goods);
    /**
     * 前台回调验证
     * @Author  科帮网
     * @param valideData
     * @param encoding
     * @return  String
     * @Date    2017年8月2日
     * 更新日志
     * 2017年8月2日  科帮网 首次创建
     *
     */
    String handNotify(Map<String, String> valideData, String encoding);
    /**
     * 对账单下载
     * @Author  科帮网  void
     * @Date    2017年8月2日
     * 更新日志
     * 2017年8月2日  科帮网 首次创建
     *
     */
    void fileTransfer();
}
