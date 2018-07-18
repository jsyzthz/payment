package me.jtx.robinia.payment.model;

public class Order {
    private double price=18.18D;
    private String goodsName="测试Web融合支付";
    private String payee="中国人民银行";
    private String appId="12345679";
    private String timeStamp="2018-07-15 12:45:56";
    private String nonceStr="test123456";
    private String packageName="123456";
    private String signType="MD5";
    private String paySign="123456798";
    private OrderStatus orderStatus;
    private PayType payType;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getPayee() {
        return payee;
    }
    public void setPayee(String payee) {
        this.payee = payee;
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getNonceStr() {
        return nonceStr;
    }
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getPaySign() {
        return paySign;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public PayType getPayType() {
        return payType;
    }
    public void setPayType(PayType payType) {
        this.payType = payType;
    }
}
