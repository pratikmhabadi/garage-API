package com.garage.garage.entities;


public class Message {

    private String status;
    private String message;
    private String error;

    public Message(String status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public Message(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Message() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
