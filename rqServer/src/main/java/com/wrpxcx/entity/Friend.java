package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 好友信息
 * @time: 2020-05-15 17:52
 **/
public class Friend {

    private String myId;
    private String friendId;
    private String friendGroupId;   //所在分组名称

    public Friend(){}

    public Friend(String myId, String friendId, String friendGroupId) {
        this.myId = myId;
        this.friendId = friendId;
        this.friendGroupId = friendGroupId;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendGroupId() {
        return friendGroupId;
    }

    public void setFriendGroupId(String friendGroupId) {
        this.friendGroupId = friendGroupId;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "myId='" + myId + '\'' +
                ", friendId='" + friendId + '\'' +
                ", friendGroupId='" + friendGroupId + '\'' +
                '}';
    }
}
