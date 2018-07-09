package me.jtx.robinia.payment.trade.alipay.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import me.jtx.robinia.payment.trade.alipay.config.Constants;
import me.jtx.robinia.payment.trade.alipay.model.ExtendParams;
import me.jtx.robinia.payment.trade.alipay.model.GoodsDetail;
import me.jtx.robinia.payment.trade.alipay.model.builder.AlipayTradePayRequestBuilder;

/**
 * @author huazhong
 * @date 2018/07/09
 */
public class PayVO {
    @NotNull(message = "商户订单号不能为空")
    private String tradeNo;
    @NotNull(message = "订单名称不能为空")
    private String subject;
    @NotNull(message = "付款金额不能为空")
    private String totalAmount;
    private String undiscountableAmount;
    @NotNull(message = "付款条码不能为空")
    private String authCode;
    private String sellerId;
    private String body;
    private String operatorId;
    @NotNull(message = "商户门店编号不能为空")
    private String storeId;
    private String providerId;
    private List<GoodsDetail> goods;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUndiscountableAmount() {
        return undiscountableAmount;
    }

    public void setUndiscountableAmount(String undiscountableAmount) {
        this.undiscountableAmount = undiscountableAmount;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public List<GoodsDetail> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsDetail> goods) {
        this.goods = goods;
    }

    public AlipayTradePayRequestBuilder getBuilder() {
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId(providerId);
        // 创建条码支付请求builder，设置请求参数
        AlipayTradePayRequestBuilder builder =
            new AlipayTradePayRequestBuilder().setOutTradeNo(tradeNo).setSubject(subject).setAuthCode(authCode)
                .setTotalAmount(totalAmount).setStoreId(storeId).setUndiscountableAmount(undiscountableAmount)
                .setBody(body).setOperatorId(operatorId).setExtendParams(extendParams).setSellerId(sellerId)
                .setGoodsDetailList(goods).setTimeoutExpress(Constants.TIMEOUT_EXPRESS);
        return builder;
    }
}
