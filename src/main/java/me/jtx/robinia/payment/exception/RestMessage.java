package me.jtx.robinia.payment.exception;

import java.util.List;

public class RestMessage {
    private String message;
    private List<String> messages;

    public RestMessage(List<String> messages) {
        this.messages = messages;
    }

    public RestMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getMessages() {
        return messages;
    }
}
