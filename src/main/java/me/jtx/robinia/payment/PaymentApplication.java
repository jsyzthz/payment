package me.jtx.robinia.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.jtx.robinia.payment.trade.alipay.config.Configs;
import me.jtx.robinia.payment.unionpay.acp.sdk.SDKConfig;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
		Configs.init("zfbinfo.properties");
		SDKConfig.getConfig().loadPropertiesFromSrc();
	}
}
