package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 好友的详细信息
 * @time: 2020-05-16 09:31
 **/
public class FriendDetail extends Friend{

    private String friendName;
    private String friendSign;
    private String friendHeadImg;
    private String friendGroupName;

    public FriendDetail(String friendName, String friendSign, String friendHeadImg, String friendGroupName) {
        this.friendName = friendName;
        this.friendSign = friendSign;
        this.friendHeadImg = friendHeadImg;
        this.friendGroupName = friendGroupName;
    }

    public FriendDetail(String myId, String friendId, String friendGroupId, String friendName, String friendSign, String friendHeadImg, String friendGroupName) {
        super(myId, friendId, friendGroupId);
        this.friendName = friendName;
        this.friendSign = friendSign;
        this.friendHeadImg = friendHeadImg;
        this.friendGroupName = friendGroupName;
    }

    public FriendDetail(){}

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
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

    public String getFriendGroupName() {
        return friendGroupName;
    }

    public void setFriendGroupName(String friendGroupName) {
        this.friendGroupName = friendGroupName;
    }

    @Override
    public String toString() {
        return "FriendDetail{" +
                "friendName='" + friendName + '\'' +
                ", friendSign='" + friendSign + '\'' +
                ", friendHeadImg='" + friendHeadImg + '\'' +
                ", friendGroupName='" + friendGroupName + '\'' +
                '}';
    }
}
