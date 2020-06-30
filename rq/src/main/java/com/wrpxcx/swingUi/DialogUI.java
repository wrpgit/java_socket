package com.wrpxcx.swingUi;

import com.alibaba.fastjson.JSONObject;
import com.wrpxcx.clientSocket.Client;
import com.wrpxcx.clientSocket.ClientSingleton;
import com.wrpxcx.entity.DialogMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: wrp
 * @TODO: 消息对话框
 * @time: 2020-05-28 08:33
 **/
public class DialogUI {
    private JFrame frame;
    private JPanel topPanel;
    private JLabel userHeadLabel;
    private JLabel userNameLabel;
    private JButton closeDialogButton;
    private JScrollPane bodyScrollPanel;
    private JPanel bodyPanel;
    private JScrollPane inputScrollPanel;
    private JTextArea inputTextArea;
    private JPanel inputPanel;
    private JButton sendMessageButton;

    private int y = 20;  //记录当前对话的定位  用于确定每条消息的y坐标

    private String myId;
    private String friendId;
    private String myUserName;
    private String friendName;
    private String myHeadImg;
    private String friendHeadImg;


    public DialogUI(String friendId, String myId, String myUserName, String myHeadImg){
        //通过friendId 向服务器 拿到好友的信息
        this.friendId = friendId;
        this.myId = myId;
        this.myUserName = myUserName;
        this.myHeadImg = myHeadImg;
        init();
    }


    public void init() {
        frame = new JFrame();
        topPanel = new JPanel(null);
        userHeadLabel = new JLabel();
        userNameLabel = new JLabel();
        closeDialogButton = new JButton("关闭");
        bodyScrollPanel = new JScrollPane();
        bodyPanel = new JPanel(null);
        inputScrollPanel = new JScrollPane();
        inputTextArea = new JTextArea();
        inputPanel = new JPanel(null);
        sendMessageButton = new JButton("发送");

        frame.setSize(800, 700);
        frame.setBackground(new Color(204, 204, 204));
        frame.setLayout(null);

        topPanel.setBounds(0, 0, 800, 60);
        topPanel.setBackground(new Color(26,64,135));
        userHeadLabel.setBounds(300, 5, 50,50);

        topPanel.add(userHeadLabel);
        userNameLabel.setBounds(370, 10, 200, 30);
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setText(myUserName);
        topPanel.add(userNameLabel);
        frame.add(topPanel);

        bodyScrollPanel.setBounds(0, 60, 780, 400);
        bodyScrollPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        //bodyPanel.setPreferredSize(new Dimension(760, 700));  //后续需要根据消息长度修改
        bodyScrollPanel.setViewportView(bodyPanel);
        frame.add(bodyScrollPanel);


        inputScrollPanel.setBounds(0, 462, 800, 155);
        inputScrollPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        inputTextArea.setBounds(0, 0, 800, 100);
        inputTextArea.setPreferredSize(new Dimension(760, 200));
        //inputPanel.setBounds(0, 0, 800, 100);
        //inputPanel.setPreferredSize(new Dimension(800, 700));
        inputTextArea.setLineWrap(true);
        inputTextArea.setMargin(new Insets(10, 10, 10, 10));
        inputTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int len = inputTextArea.getText().length();
                if(len > 80) {
                    System.out.println("超出长度");
                    String newStr = inputTextArea.getText().substring(0, 80);
                    inputTextArea.setText(newStr);
                }
            }
        });
        inputScrollPanel.setViewportView(inputTextArea);

        frame.add(inputScrollPanel);

        sendMessageButton.setBounds(700, 620, 70, 30);
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("点击了按钮");
                if(inputTextArea.getText() == "") {
                    return;
                }
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //System.out.println("当前时间：" + sdf.format(d));

                Client client = ClientSingleton.getClient();
                DialogMessage message = new DialogMessage(myId, friendId, inputTextArea.getText(), sdf.format(d), 0);
                addMessage(message);
                inputTextArea.setText("");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("operation", "sendMessage");
                jsonObject.put("message", message);
                client.sendMessage(jsonObject.toString());
            }

        });
        frame.add(sendMessageButton);

        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                RqUI.getDialogBox().remove(friendId);
            }
        });

        Client client = ClientSingleton.getClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", "dialogInfo");
        jsonObject.put("friendId", friendId);
        jsonObject.put("myId", myId);
        client.sendMessage(jsonObject.toString());   //请求好友的信息， 返回的消息在方法setFriendInfo中处理

    }

    public void setFriendInfo(String friendName, String friendHeadImg) {
        this.friendName = friendName;
        this.friendHeadImg = friendHeadImg;
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/head/" + friendHeadImg + ".png"));
        icon.setImage((icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        userHeadLabel.setIcon(icon);
        userNameLabel.setText(friendName);
    }

    public void addMessage(DialogMessage dialogMessage) {
        if(dialogMessage.getFromId().equals(myId)) {
            //我是发送方
           JPanel message = new JPanel(null);
           message.setBounds(230, y, 500, 100);
           y += 120;
           bodyPanel.setPreferredSize(new Dimension(760, y));  //修改可见区域
           message.setBackground(Color.gray);
           bodyPanel.add(message);

           JLabel headImgLabel = new JLabel();
           ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/head/" + myHeadImg + ".png"));
           icon.setImage((icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
           headImgLabel.setIcon(icon);
           headImgLabel.setBounds(430, 30, 50, 50);
           message.add(headImgLabel);


           JLabel timeLabel = new JLabel();
           timeLabel.setText(dialogMessage.getMessageTime());
           timeLabel.setBounds(350, 0, 150, 30);
           message.add(timeLabel);

           JLabel context = new JLabel();
           context.setBounds(5, 0, 370, 60);
           context.setText(dialogMessage.getMessage());
           JLabelSetText(context, dialogMessage.getMessage());

           JPanel textPanel = new JPanel(null);
           textPanel.setBounds(30, 30, 370, 60);
           textPanel.setBackground(Color.WHITE);

           textPanel.add(context);
           message.add(textPanel);

           bodyScrollPanel.getViewport().setViewPosition(new Point(0, bodyScrollPanel.getVerticalScrollBar().getMaximum()));
           bodyPanel.updateUI();
        }
        else {
            //我是接收方
            JPanel message = new JPanel(null);
            message.setBounds(0, y, 500, 100);
            y += 120;
            bodyPanel.setPreferredSize(new Dimension(760, y));  //修改可见区域
            message.setBackground(Color.gray);
            bodyPanel.add(message);

            JLabel headImgLabel = new JLabel();
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/head/" + friendHeadImg + ".png"));
            icon.setImage((icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
            headImgLabel.setIcon(icon);
            headImgLabel.setBounds(20, 30, 50, 50);
            message.add(headImgLabel);

            JLabel timeLabel = new JLabel();
            timeLabel.setText(dialogMessage.getMessageTime());
            timeLabel.setBounds(40, 0, 140, 30);
            message.add(timeLabel);

            JLabel context = new JLabel();
            context.setBounds(5, 0, 370, 60);
            context.setText(dialogMessage.getMessage());
            JLabelSetText(context, dialogMessage.getMessage());

            JPanel textPanel = new JPanel(null);

            textPanel.setBounds(90, 30, 370, 60);
            textPanel.setBackground(Color.WHITE);

            textPanel.add(context);
            message.add(textPanel);

            bodyScrollPanel.getViewport().setViewPosition(new Point(0, bodyScrollPanel.getVerticalScrollBar().getMaximum()));
            bodyPanel.updateUI();

        }
    }

    private void JLabelSetText(JLabel jLabel, String longString) {

        StringBuilder builder = new StringBuilder("<html>");
        char[] chars = longString.toCharArray();
        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len++;
                if (start + len > longString.length())break;
                if (fontMetrics.charsWidth(chars, start, len)
                        > jLabel.getWidth()) {
                    break;
                }
            }
            builder.append(chars, start, len-1).append("<br/>");
            start = start + len - 1;
            len = 0;
        }
        builder.append(chars, start, longString.length()-start);
        builder.append("</html>");
        jLabel.setText(builder.toString());
    }

}
