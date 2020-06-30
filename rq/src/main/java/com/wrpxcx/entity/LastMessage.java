package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 消息列表显示的信息
 * @time: 2020-05-15 16:09
 **/
public class LastMessage {

    private String fromId;
    private String fromHeadImg;
    private String fromName;
    private String toId;
    private String toHeadImg;
    private String toName;
    private String lastMessage;
    private String lastTime;
    private int notReadNum;

    public LastMessage(){}

    public LastMessage(String fromId, String fromHeadImg, String fromName, String toId, String toHeadImg, String toName, String lastMessage, String lastTime, int notReadNum) {
        this.fromId = fromId;
        this.fromHeadImg = fromHeadImg;
        this.fromName = fromName;
        this.toId = toId;
        this.toHeadImg = toHeadImg;
        this.toName = toName;
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

    public String getFromHeadImg() {
        return fromHeadImg;
    }

    public void setFromHeadImg(String fromHeadImg) {
        this.fromHeadImg = fromHeadImg;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToHeadImg() {
        return toHeadImg;
    }

    public void setToHeadImg(String toHeadImg) {
        this.toHeadImg = toHeadImg;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
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

    public int getNotReadNum() {
        return notReadNum;
    }

    public void setNotReadNum(int notReadNum) {
        this.notReadNum = notReadNum;
    }

    @Override
    public String toString() {
        return "LastMessage{" +
                "fromId='" + fromId + '\'' +
                ", fromHeadImg='" + fromHeadImg + '\'' +
                ", fromName='" + fromName + '\'' +
                ", toId='" + toId + '\'' +
                ", toHeadImg='" + toHeadImg + '\'' +
                ", toName='" + toName + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", notReadNum=" + notReadNum +
                '}';
    }
}
