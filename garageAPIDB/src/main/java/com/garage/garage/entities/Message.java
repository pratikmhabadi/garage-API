package com.garage.garage.entities;


public class Message {

    private String message;
    private boolean status;

    public Message(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public Message() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
