package com.example.letschat.Models;

public class Message {
    String uId,message;
    Long timestamp;

    public Message(String uId, String message, Long timestamp) {
        this.uId = uId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Message(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }
    public Message(){}

    public Message(String senderId, String receiveId, String message, long time) {
    }

    public String getuId() {
        return uId;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
