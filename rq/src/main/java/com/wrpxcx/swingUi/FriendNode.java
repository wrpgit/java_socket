package com.wrpxcx.swingUi;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author: wrp
 * @TODO: 树状展示的好友的节点，即好友显示
 * @time: 2020-05-12 20:29
 **/
public class FriendNode extends DefaultMutableTreeNode {

    private String headImage;
    private String userName;
    private String userSign;
    private int isOnline;
    private String userId;

    private String groupId;

    public JPanel cateContent;
    public JPanel nodeContent;
    public JLabel iconLabel;
    public JLabel nameLabel;
    public JLabel signLabel;

    public FriendNode(String headImage, String userName, String userSign, int isOnline, String userId, String groupId) {
        super();
        this.headImage = headImage;
        this.userName = userName;
        this.userSign = userSign;
        this.isOnline = isOnline;
        this.userId = userId;
        this.groupId = groupId;
        initNodeGUI();
    }

    public FriendNode(String groupName, String groupId) {
        this.userName = groupName;
        this.groupId = groupId;
        initCateGUI();
    }

    public void initCateGUI(){
        cateContent = new JPanel(null);
        cateContent.setOpaque(false);
        cateContent.setPreferredSize(new Dimension(400, 25));
        iconLabel = new JLabel();
        iconLabel.setBounds(7,  7, 20, 16);
        cateContent.add(iconLabel);

        nameLabel = new JLabel(userName);
        nameLabel.setBounds(23, 0, 132, 28);
        cateContent.add(nameLabel);

        //cateContent.setBorder(BorderFactory.createLineBorder(Color.red, 1));
    }

    public void initNodeGUI() {
        nodeContent = new JPanel(null);
        nodeContent.setOpaque(false);
        nodeContent.setPreferredSize(new Dimension(400, 60));

        iconLabel = new JLabel();
        iconLabel.setBounds(6, 5, 40, 40);
        nodeContent.add(iconLabel);

        nameLabel = new JLabel(userName);
        nameLabel.setBounds(59, 5, 132, 14);
        nodeContent.add(nameLabel);

        signLabel = new JLabel(userSign);
        signLabel.setBounds(59, 27, 132, 14);
        nodeContent.add(signLabel);

        //nodeContent.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        nodeContent.setBorder(BorderFactory.createEtchedBorder());
    }
    public Component getNodeView(){

        return nodeContent;
    }
    public Component getCateView() {

        return cateContent;
    }


    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
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

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

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


    @Override
    public String toString() {
        return "FriendNode{" +
                "headImage=" + headImage +
                ", userName='" + userName + '\'' +
                ", userSign='" + userSign + '\'' +
                ", isOnline=" + isOnline +
                ", userId='" + userId + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}

