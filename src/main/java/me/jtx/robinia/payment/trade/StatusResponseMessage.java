package me.jtx.robinia.payment.trade;

public class StatusResponseMessage extends ResponseMessage {
    private boolean status;

    public StatusResponseMessage() {
        super();
    }

    public StatusResponseMessage(String code, String message, boolean status) {
        super(code, message);
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
