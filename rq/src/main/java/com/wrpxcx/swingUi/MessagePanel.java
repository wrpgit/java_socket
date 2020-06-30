package com.wrpxcx.swingUi;

import com.wrpxcx.util.MouseClickedTwiceListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

/**
 * @author: wrp
 * @TODO: 重写自己的消息panel  每条消息为一个panel
 * @time: 2020-05-13 15:39
 **/
public class MessagePanel {

    private String userName;
    private String lastMessage;
    private String lastTime;
    private String friendHeadImage;
    private String userId;

    private int x = 0, y = 0;

    public JLabel nameLabel;
    public JLabel messageLabel;
    public JLabel timeLabel;
    public JLabel headImageLabel;

    public JPanel res;

    public MessagePanel(String userName, String lastMessage, String lastTime, String friendHeadImage, String userId) {

        this.userName = userName;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
        this.friendHeadImage = friendHeadImage;
        this.userId = userId;
        init();
    }

    public void init() {
        res = new JPanel(null);
        res.setBounds(0, y, 400, 50);


        nameLabel = new JLabel(userName);
        nameLabel.setBounds(50, 3, 270, 17);
        res.add(nameLabel);

        messageLabel = new JLabel(lastMessage);
        messageLabel.setBounds(50, 25, 335, 17);
        res.add(messageLabel);

        timeLabel = new JLabel(lastTime);
        timeLabel.setBounds(200, 3, 200, 17);
        res.add(timeLabel);

        headImageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/head/" + friendHeadImage + ".png"));
        icon.setImage((icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        headImageLabel.setBounds(6, 6, 40, 40);
        headImageLabel.setIcon(icon);
        res.add(headImageLabel);

//        res.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                res.setBackground(Color.GRAY);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                res.setBackground(Color.WHITE);
//            }
//
//        });

        res.addMouseListener(new MouseClickedTwiceListener(userId));

    }

    public JPanel getMessagePanel(){

        res.setBounds(0, y, 400, 50);
        return res;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFriendHeadImage() {
        return friendHeadImage;
    }

    public void setFriendHeadImage(String friendHeadImage) {
        this.friendHeadImage = friendHeadImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * @Author wrp
     * @Description //实现双击事件的监听
     * @Date  2020/5/27
     **/

}
