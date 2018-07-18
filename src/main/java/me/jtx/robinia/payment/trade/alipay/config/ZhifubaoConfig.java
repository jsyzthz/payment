package me.jtx.robinia.payment.trade.alipay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alipay")
public class ZhifubaoConfig {
    private String openApiDomain;
    private String pid;
    private String appid;
    private String privateKey;
    private String publicKey;
    private String alipayPublicKey;
    private String signType;
    private String maxQueryRetry;
    private String queryDuration;
    private String maxCancelRetry;
    private String cancelDuration;
    private String heartBeatDelay;
    private String heartBeatDuration;

    public String getOpenApiDomain() {
        return openApiDomain;
    }

    public void setOpenApiDomain(String openApiDomain) {
        this.openApiDomain = openApiDomain;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public void setMaxQueryRetry(String maxQueryRetry) {
        this.maxQueryRetry = maxQueryRetry;
    }

    public String getQueryDuration() {
        return queryDuration;
    }

    public void setQueryDuration(String queryDuration) {
        this.queryDuration = queryDuration;
    }

    public String getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public void setMaxCancelRetry(String maxCancelRetry) {
        this.maxCancelRetry = maxCancelRetry;
    }

    public String getCancelDuration() {
        return cancelDuration;
    }

    public void setCancelDuration(String cancelDuration) {
        this.cancelDuration = cancelDuration;
    }

    public String getHeartBeatDelay() {
        return heartBeatDelay;
    }

    public void setHeartBeatDelay(String heartBeatDelay) {
        this.heartBeatDelay = heartBeatDelay;
    }

    public String getHeartBeatDuration() {
        return heartBeatDuration;
    }

    public void setHeartBeatDuration(String heartBeatDuration) {
        this.heartBeatDuration = heartBeatDuration;
    }
}
