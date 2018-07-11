package me.jtx.robinia.payment.exception;

/**
 * @author huazhong
 * @date 2018/07/11
 */

public class RestException extends RuntimeException {
    private static final long serialVersionUID = -7986184149529698508L;

    private String message;
    private Object[] args;

    public RestException(String message, Object[] args) {
        super();
        this.message = message;
        this.args = args;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}