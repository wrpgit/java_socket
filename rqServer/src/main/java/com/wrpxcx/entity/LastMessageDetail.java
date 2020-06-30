package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 返回给客户端的详细信息，从数据库中查询的结果
 * @time: 2020-05-16 09:03
 **/
public class LastMessageDetail extends LastMessage{

    private String fromName;
    private String fromHeadImg;

    private String toName;
    private String toHeadImg;


    public LastMessageDetail(String fromUserId, String toUserId, String lastMessage, String lastTime, int notReadNUm, String fromName, String fromHeadImg, String toName, String toHeadImg) {

        super(fromUserId, toUserId, lastMessage, lastTime, notReadNUm);
        this.fromName = fromName;
        this.fromHeadImg = fromHeadImg;
        this.toName = toName;
        this.toHeadImg = toHeadImg;
    }

    public LastMessageDetail(String fromName, String fromHeadImg, String toName, String toHeadImg) {
        this.fromName = fromName;
        this.fromHeadImg = fromHeadImg;
        this.toName = toName;
        this.toHeadImg = toHeadImg;
    }

    public LastMessageDetail() {}

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromHeadImg() {
        return fromHeadImg;
    }

    public void setFromHeadImg(String fromHeadImg) {
        this.fromHeadImg = fromHeadImg;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToHeadImg() {
        return toHeadImg;
    }

    public void setToHeadImg(String toHeadImg) {
        this.toHeadImg = toHeadImg;
    }

    @Override
    public String toString() {
        return "LastMessageDetail{" +
                "fromName='" + fromName + '\'' +
                ", fromHeadImg='" + fromHeadImg + '\'' +
                ", toName='" + toName + '\'' +
                ", toHeadImg='" + toHeadImg + '\'' +
                '}';
    }
}
