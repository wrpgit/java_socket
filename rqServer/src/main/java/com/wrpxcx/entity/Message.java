package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 每条消息的信息
 * @time: 2020-05-16 09:21
 **/
public class Message {
    private String fromId;
    private String toId;
    private String message;
    private String messageTime;
    private int status;

    public Message(String fromId, String toId, String message, String messageTime, int status) {
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.messageTime = messageTime;
        this.status = status;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", message='" + message + '\'' +
                ", messageTime='" + messageTime + '\'' +
                ", status=" + status +
                '}';
    }
}
