package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 最后一条消息  用于展示消息列表
 * @time: 2020-05-15 17:53
 **/
public class LastMessage {

    private String fromId;
    private String toId;
    private String lastMessage;
    private String lastTime;
    private int notReadNum;

    public LastMessage() {}

    public LastMessage(String fromId, String toId, String lastMessage, String lastTime, int notReadNum) {
        this.fromId = fromId;
        this.toId = toId;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
        this.notReadNum = notReadNum;
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

    public int getNotReadNum() {
        return notReadNum;
    }

    public void setNotReadNum(int notReadNum) {
        this.notReadNum = notReadNum;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "LastMessage{" +
                "fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", notReadNum=" + notReadNum +
                '}';
    }
}
