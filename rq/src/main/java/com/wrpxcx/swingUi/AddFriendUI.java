package com.wrpxcx.swingUi;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import com.wrpxcx.clientSocket.Client;
import com.wrpxcx.clientSocket.ClientSingleton;
import com.wrpxcx.entity.Friend;
import com.wrpxcx.entity.Group;
import com.wrpxcx.entity.User;
import sun.misc.Cleaner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author: wrp
 * @TODO: 添加好友的页面UI
 * @time: 2020-05-31 19:50
 **/
public class AddFriendUI {

    private JFrame frame;
    private JLabel headImg;
    private JLabel userNameLabel;
    private JLabel userSignLabel;
    private JLabel chooseTitleLabel;
    private JComboBox chooseGroup;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel bodyPanel;
    private JTextField inputRqId;
    private JButton searchButton;

    private JLabel showRes;

    private List<Group> groups = new ArrayList<>();
    private String friendId;  //点击搜索后，保存搜索的值，防止搜索后 修改了，  直接添加好友

    public static void main(String[] args) {
        AddFriendUI addFriendUI = new AddFriendUI();
        addFriendUI.init();
    }
    public AddFriendUI(){
        init();
    }
    public void init() {
        frame = new JFrame();
        frame.setLayout(null);
        headImg = new JLabel();
        userNameLabel = new JLabel("昵称");
        userSignLabel = new JLabel("个性签名");
        chooseTitleLabel = new JLabel("选择分组");
        chooseGroup = new JComboBox();
        confirmButton = new JButton("添加好友");
        cancelButton = new JButton("取消");
        searchButton = new JButton("搜索");
        bodyPanel = new JPanel(null);
        inputRqId = new JTextField();
        showRes = new JLabel("没有找到该用户");


        frame.setSize(460, 450);

        inputRqId.setBounds(40, 25, 245, 30);
        frame.add(inputRqId);
        searchButton.setBounds(305, 25, 80, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = inputRqId.getText();
                showRes.setVisible(false);
                System.out.println(userId);
                bodyPanel.setVisible(false);
                if(userId.equals("")) {
                    showRes.setText("RQ号不能为空");
                    showRes.setVisible(true);
                }
                else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("operation", "searchUser");
                    jsonObject.put("userId", userId);
                    jsonObject.put("myId", RqUI.getMyId());
                    Client client = ClientSingleton.getClient();
                    client.sendMessage(jsonObject.toString());
                    friendId = userId;

                }
            }
        });

        frame.add(searchButton);

        showRes.setBounds(45, 65, 200, 25);
        showRes.setVisible(false);
        frame.add(showRes);

        bodyPanel.setBounds(0, 60, 400, 400);
        headImg.setBounds(30, 30, 50, 50);
        userNameLabel.setBounds(110, 40, 130, 30);
        userSignLabel.setBounds(110, 85, 200, 30);

        chooseTitleLabel.setBounds(25, 140, 75, 30);
        chooseGroup.setBounds(110, 135, 140, 40);

        confirmButton.setBounds(55, 240, 120, 30);
        cancelButton.setBounds(210, 240, 120, 30);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("operation", "addFriend");
                jsonObject.put("friendId", friendId);
                jsonObject.put("myId", RqUI.getMyId());

                int index = chooseGroup.getSelectedIndex();
                String groupId = groups.get(index).getGroupId();
                jsonObject.put("groupId", groupId);
                Client client = ClientSingleton.getClient();
                client.sendMessage(jsonObject.toString());

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                UIFactory.closeAddFriendUI();
            }
        });

        bodyPanel.add(headImg);
        bodyPanel.add(userNameLabel);
        bodyPanel.add(userSignLabel);
        bodyPanel.add(chooseTitleLabel);
        bodyPanel.add(chooseGroup);
        bodyPanel.add(confirmButton);
        bodyPanel.add(cancelButton);
        bodyPanel.setVisible(false);

        frame.add(bodyPanel);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIFactory.closeAddFriendUI();
                super.windowClosing(e);
            }
        });

    }

    public void dealSearchResult(JSONObject jsonObject) {
        int status = jsonObject.getInteger("status");
        if(0 == status) {
            showRes.setText("该用户不存在, 请更改id后重新搜索");
            showRes.setVisible(true);
        } else {
            bodyPanel.setVisible(true);
            User user = jsonObject.getObject("userInfo", User.class);
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/head/" + user.getUserHeadImg() + ".png"));
            icon.setImage((icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            headImg.setIcon(icon);
            userNameLabel.setText("昵称： " + user.getUserName());
            userSignLabel.setText("个性签名： " + user.getUserSign());

            List<Group> g = jsonObject.getObject("group", new TypeReference<List<Group>>(){});
            chooseGroup.removeAllItems();
            groups.clear();
            for (Group t: g) {
                chooseGroup.addItem(t.getGroupName());
                groups.add(t);
            }
        }
    }

    public void dealAddFriendResult(JSONObject jsonObject) {
        if(1 == jsonObject.getInteger("status")) {
            //已经是好友了
            showRes.setText("已经是好友了");
            showRes.setVisible(true);
        }
        else{
            showRes.setText("添加成功");
            showRes.setVisible(true);
            RqUI rqUI = UIFactory.getRqUI();
            Friend friend = jsonObject.getObject("friend", Friend.class);
            rqUI.addFriend(friend);

        }
    }

}
