package me.jtx.robinia.payment.trade.alipay.vo;

/**
 * @author huazhong
 * @date 2018/07/09
 */
public class ResponseMessage {
    private String code;
    private String message;

    public ResponseMessage() {}

    public ResponseMessage(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
