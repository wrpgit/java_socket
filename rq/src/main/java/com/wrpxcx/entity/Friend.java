package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 好友列表所展示的信息
 * @time: 2020-05-15 16:22
 **/
public class Friend {

    private String friendName;
    private String friendId;
    private String friendSign;
    private String friendHeadImg;
    private String friendGroupId;

    public Friend(){}

    public Friend(String friendName, String friendId, String friendSign, String friendHeadImg, String friendGroupId) {
        this.friendName = friendName;
        this.friendId = friendId;
        this.friendSign = friendSign;
        this.friendHeadImg = friendHeadImg;
        this.friendGroupId = friendGroupId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendSign() {
        return friendSign;
    }

    public void setFriendSign(String friendSign) {
        this.friendSign = friendSign;
    }

    public String getFriendHeadImg() {
        return friendHeadImg;
    }

    public void setFriendHeadImg(String friendHeadImg) {
        this.friendHeadImg = friendHeadImg;
    }

    public String getFriendGroupId() { return friendGroupId; }

    public void setFriendGroupId(String friendGroupId) { this.friendGroupId = friendGroupId; }



    @Override
    public String toString() {
        return "Friend{" +
                "friendName='" + friendName + '\'' +
                ", friendId='" + friendId + '\'' +
                ", friendSign='" + friendSign + '\'' +
                ", friendHeadImg='" + friendHeadImg + '\'' +
                ", friendGroupId=" + friendGroupId +
                '}';
    }
}
