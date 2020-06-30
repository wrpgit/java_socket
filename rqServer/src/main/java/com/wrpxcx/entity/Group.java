package com.wrpxcx.entity;

/**
 * @author: wrp
 * @TODO: 分组信息
 * @time: 2020-05-16 14:57
 **/
public class Group {
    private String groupId;
    private String userId;
    private String groupName;

    public Group(String groupId, String userId, String groupName) {
        this.groupId = groupId;
        this.userId = userId;
        this.groupName = groupName;
    }

    public Group(){}

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", userId='" + userId + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
