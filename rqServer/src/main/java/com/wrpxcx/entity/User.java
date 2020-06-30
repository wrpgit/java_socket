package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 用户信息
 * @time: 2020-05-16 08:32
 **/
public class User {

    private String userId;
    private String userName;
    private String userSign;
    private String userHeadImg;

    public User(String userId, String userName, String userSign, String userHeadImg) {
        this.userId = userId;
        this.userName = userName;
        this.userSign = userSign;
        this.userHeadImg = userHeadImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userSign='" + userSign + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                '}';
    }
}

