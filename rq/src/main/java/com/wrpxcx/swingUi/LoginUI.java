package com.wrpxcx.swingUi;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wrpxcx.clientSocket.Client;
import com.wrpxcx.clientSocket.ClientSingleton;
import com.wrpxcx.entity.Friend;
import com.wrpxcx.entity.Group;
import com.wrpxcx.entity.LastMessage;
import com.wrpxcx.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginUI {

    private JFrame frame;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JPanel mainPanel;
    private JLabel userLabel;
    private JTextField userText;
    private JButton signButton;
    private JLabel prompt;
    private JButton signUpButton;

    public static void main(String[] args) {

        LoginUI loginUI = UIFactory.getLoginUI();
        loginUI.init();

    }

    public void init(){
        frame = new JFrame("RQ");
        mainPanel = new JPanel(null);
        userText = new JTextField();
        userLabel = new JLabel();
        passwordText = new JPasswordField();
        passwordLabel= new JLabel();
        signButton = new JButton();
        signUpButton = new JButton("注册");

        prompt = new JLabel();

        userLabel.setText("账号: ");
        userLabel.setBounds(30, 20, 80, 25);
        mainPanel.add(userLabel);

        userText.setBounds(100, 20, 165, 25);
        mainPanel.add(userText);

        passwordLabel.setText("密码：");
        passwordLabel.setBounds(30, 50, 80, 25);
        mainPanel.add(passwordLabel);

        passwordText.setBounds(100, 50, 165, 25);
        mainPanel.add(passwordText);

        signButton.setText("安全登录");
        signButton.setBounds(30, 150, 100, 25);
        mainPanel.add(signButton);

        signUpButton.setBounds(170, 150, 70, 25);
        mainPanel.add(signUpButton);

        prompt.setBounds(30, 90, 100, 20);
        prompt.setText("账号或密码错误");
        prompt.setVisible(false);
        mainPanel.add(prompt);

        frame.setSize(400, 270);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        signButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //使用新线程去发起请求，不至于主页面卡死
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Client client = ClientSingleton.getClient();
                                System.out.println("发起登录请求");
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("operation", "login");
                                jsonObject.put("userId", userText.getText());
                                jsonObject.put("password", passwordText.getPassword());
                                client.sendMessage(jsonObject.toString());
                            }
                        }).start();
                    }
                }
        );
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpUI signUpUI = UIFactory.getSignUpUI();
            }
        });

    }
    public void dealLoginBack(JSONObject jsonObject){
        int status = jsonObject.getInteger("status");
        if(status == 200) {
            System.out.println("登录成功");
            RqUI rqUI = UIFactory.getRqUI();

            //JSONObject userInfo = jsonObject.getJSONObject("userInfo");
            //将userInfo 传进去，初始化主页面
            System.out.println(jsonObject);

            User userInfo = jsonObject.getObject("userInfo", User.class);
            rqUI.setMyInfo(userInfo.getUserName(), userInfo.getUserSign(), userInfo.getUserHeadImg(), userInfo.getUserId());

            List<Friend> friends = jsonObject.getObject("friends", new TypeReference<List<Friend>>(){});
            List<Group> groups = jsonObject.getObject("groups", new TypeReference<List<Group>>(){});
            List<LastMessage> lastMessages = jsonObject.getObject("lastMessage", new TypeReference<List<LastMessage>>(){});
            for (Group t : groups) {
                //创建分组
                rqUI.addGroup(new FriendNode(t.getGroupName(), t.getGroupId()));
            }
            for (Friend t : friends) {
                //将好友显示到界面上去
                rqUI.addFriend(t);
            }

            for(LastMessage t : lastMessages) {
                System.out.println(t);
                if(t.getFromId().equals(userInfo.getUserId())) {
                    //我是发送者
                    rqUI.addMessage(new MessagePanel(t.getToName(), t.getLastMessage(), t.getLastTime(), t.getToHeadImg(), t.getToId()));
                }
                else{
                    //我是接收者
                    rqUI.addMessage(new MessagePanel(t.getFromName(), t.getLastMessage(), t.getLastTime(), t.getFromHeadImg(), t.getFromId()));
                }

            }

            frame.setVisible(false);

        } else {
            System.out.println("账号或密码错误");
            prompt.setVisible(true);
        }
    }

}
