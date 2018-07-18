package me.jtx.robinia.payment.trade.alipay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alipay.api.DefaultAlipayClient;

/**
 * @author huazhong
 * @date 2018/07/17
 */
@Component
public class RobiniaAlipayClient extends DefaultAlipayClient {
    @Value("${alipay.openApiDomain}")
    private String stringProp1;
    
    public RobiniaAlipayClient() {
        super("", "", "");
         // TODO Auto-generated constructor stub
    }


//    public RobiniaAlipayClient() {
//        super(alipayConfig.getOpenApiDomain(), alipayConfig.getAppid(), alipayConfig.getPrivateKey(), "json", "utf-8",
//            alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
//    }
    
    

}
