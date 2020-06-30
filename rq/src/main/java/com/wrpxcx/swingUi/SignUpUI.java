package com.wrpxcx.swingUi;

import com.alibaba.fastjson.JSONObject;
import com.wrpxcx.clientSocket.Client;
import com.wrpxcx.clientSocket.ClientSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

/**
 * @author: wrp
 * @TODO: 注册界面
 * @time: 2020-06-01 15:13
 **/
public class SignUpUI {

    private JFrame frame;
    private JPanel bodyPanel;
    private JLabel userNameTitle;
    private JTextField userNameField;
    private JLabel firstPasswordTitle;
    private JPasswordField firstPasswordField;
    private JLabel secondPasswordTitle;
    private JPasswordField secondPasswordField;
    private JLabel userSignTitle;
    private JTextField userSingField;
    private JButton signUpButton;

    private JLabel showPrompt;

    private JLabel res;

    public static void main(String[] args) {
        SignUpUI signUpUI = new SignUpUI();
        signUpUI.init();
    }

    public SignUpUI() {
        init();
    }
    public void init() {
        frame = new JFrame();
        frame.setLayout(null);
        bodyPanel = new JPanel(null);
        userNameTitle = new JLabel("昵称： ");
        userNameField = new JTextField();
        firstPasswordTitle = new JLabel("密码： ");
        firstPasswordField = new JPasswordField();
        secondPasswordTitle = new JLabel("重复密码： ");
        secondPasswordField = new JPasswordField();
        userSignTitle = new JLabel("个性签名: ");
        userSingField = new JTextField();
        signUpButton = new JButton("注册");
        showPrompt = new JLabel();
        res = new JLabel();


        frame.setSize(350, 350);
        bodyPanel.setBounds(0, 0, 350, 350);
        frame.add(bodyPanel);

        userNameTitle.setBounds(45, 30, 45, 20);
        userNameField.setBounds(80, 25, 170, 30);
        firstPasswordTitle.setBounds(45, 65, 45, 20);
        firstPasswordField.setBounds(80, 60, 170, 30);
        secondPasswordTitle.setBounds(15, 100, 80, 20);
        secondPasswordField.setBounds(80, 95, 170, 30);
        userSignTitle.setBounds(15, 170, 80, 20);
        userSingField.setBounds(80, 165, 170, 30);
        signUpButton.setBounds(105, 215, 80, 30);

        bodyPanel.add(userNameTitle);
        bodyPanel.add(userNameField);
        bodyPanel.add(firstPasswordTitle);
        bodyPanel.add(firstPasswordField);
        bodyPanel.add(secondPasswordTitle);
        bodyPanel.add(secondPasswordField);
        bodyPanel.add(userSignTitle);
        bodyPanel.add(userSingField);
        bodyPanel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPrompt.setVisible(false);
                String userName = userNameField.getText();
                String password1 = String.valueOf(firstPasswordField.getPassword());
                String password2 = String.valueOf(secondPasswordField.getPassword());
                String userSign = userSingField.getText();

                System.out.println(password1);
                System.out.println(password2);
                if(userName.equals("")){
                    showPrompt.setText("请输入昵称");
                    showPrompt.setVisible(true);
                    return;
                }
                if(password1.equals("")){
                    showPrompt.setText("请输入密码");
                    showPrompt.setVisible(true);
                    return;
                }
                if(!password1.equals(password2)){
                    showPrompt.setText("两次密码输入不一致");
                    showPrompt.setVisible(true);
                    return;
                }
                if(userSign.equals("")) {
                    showPrompt.setText("请输入个性签名");
                    showPrompt.setVisible(true);
                    return;
                }
                System.out.println("注册");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("operation", "signUp");
                jsonObject.put("userName", userNameField.getText());
                jsonObject.put("password", firstPasswordField.getPassword());
                jsonObject.put("userSign", userSingField.getText());
                Client client = ClientSingleton.getClient();
                client.sendMessage(jsonObject.toString());
            }
        });

        showPrompt.setBounds(85, 125, 145, 25);
        showPrompt.setVisible(false);
        bodyPanel.add(showPrompt);

        res.setBounds(40,40, 270, 200);
        res.setFont(new Font("隶书", Font.PLAIN, 30));
        //res.setText("<html><body>恭喜你注册成功！<br/> 你的RQ号为 <br/> 10086 <br/> 请牢记这个号码，这是你登陆的唯一凭证</body></html>");
        //<html><body><p align=\"center\">数据版本<br/>v1.0.0</p></body></html>"
        frame.add(res);
        res.setVisible(false);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIFactory.closeSignUI();
                super.windowClosing(e);
                //System.exit(0);

            }
        });

        frame.setVisible(true);
    }

    public void signUpReturn(JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");

        bodyPanel.setVisible(false);
        res.setText("<html><body>恭喜你注册成功！<br/> 你的RQ号为 <br/>" + userId + "<br/> 请牢记这个号码，这是你登陆的唯一凭证</body></html>");
        res.setVisible(true);
    }
}
