package com.wrpxcx.swingUi;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.FNEG;
import com.wrpxcx.clientSocket.Client;
import com.wrpxcx.clientSocket.ClientSingleton;
import com.wrpxcx.entity.Friend;
import com.wrpxcx.entity.Group;
import com.wrpxcx.util.MouseClickedTwiceListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wrp
 * @TODO: RQ的主页面
 * @time: 2020-05-11 14:43
 **/
public class RqUI {
    private JFrame frame;
    private JPanel topPanel;
    private JTabbedPane tabbedPane;
    private JPanel groupMessage;
    private JPanel groupFriend;
    private JScrollPane messageScroll;
    private JScrollPane friendScroll;
    private JPanel messageList;
    private JLabel headImg;
    private JLabel userName;
    private JLabel userSign;
    private JTextField search;

    private JTextField userSignInput;
    private JTextField userNameInput;
    private JButton updateUserSignButton;
    private JButton updateUserNameButton;
    private JButton addFriendButton;
    private JButton addGroupButton;

    private static JTree tree;
    private static FriendNode treeRoot;

    private List<MessagePanel> messageListPanel;
    private static Map<String, DialogUI> dialogBox = new ConcurrentHashMap<>();   //存放已经打开的对话框  hashTable 线程安全

    private static String myName;
    private static String mySign;
    private static String myHeadImg;
    private static String myId;

    public RqUI() {

        initMainUI();
    }

    public void initMainUI() {
        frame = new JFrame("RQ");
        frame.setSize(400, 650);
        frame.setLayout(null);

        topPanel = new JPanel(null);
        tabbedPane = new JTabbedPane();
        messageScroll = new JScrollPane();
        groupMessage = new JPanel(null);
        groupFriend = new JPanel(null);
        messageList = new JPanel(null);
        headImg = new JLabel();
        userSign = new JLabel();
        userName = new JLabel();
        search = new JTextField();
        userSignInput = new JTextField();
        userNameInput = new JTextField();

        updateUserNameButton = new JButton("保存");
        updateUserSignButton = new JButton("保存");
        addFriendButton = new JButton("添加好友");
        addGroupButton = new JButton("创建新分组");

        friendScroll = new JScrollPane();

        treeRoot = new FriendNode("root", "0");

        topPanel.setBackground(new Color(100, 100, 0));
        topPanel.setBounds(0, 0, 400, 100);
        frame.add(topPanel);

        headImg.setBounds(20, 20, 50, 50);
        headImg.setBackground(new Color(230, 230, 230));
        topPanel.add(headImg);

        userName.setBounds(100, 20, 100, 25);
        userNameInput.setBounds(100, 20, 200, 30);
        updateUserNameButton.setBounds(310, 20, 60, 30);
        userNameInput.setVisible(false);
        updateUserNameButton.setVisible(false);
        topPanel.add(userNameInput);
        topPanel.add(updateUserNameButton);
        topPanel.add(userName);
/***********修改昵称开始***********/
        userName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == e.BUTTON1) {
                    //鼠标左键事件， 让输入框聚焦
                    userName.setVisible(false);
                    userNameInput.setText(userName.getText());
                    userNameInput.setVisible(true);
                    updateUserNameButton.setVisible(true);
                    userNameInput.requestFocus();
                }
            }
        });
        updateUserNameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateUserNameButton.setVisible(false);
                userNameInput.setVisible(false);
                userName.setText(userNameInput.getText());
                userName.setVisible(true);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("operation", "changeUserName");
                jsonObject.put("userId", myId);
                jsonObject.put("newName", userName.getText());
                Client client = ClientSingleton.getClient();
                client.sendMessage(jsonObject.toString());
            }
        });
        userNameInput.addFocusListener(new FocusAdapter() {
            //失焦事件
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                updateUserNameButton.setVisible(false);
                userNameInput.setVisible(false);
                userName.setVisible(true);
            }
        });

/***********修改昵称结束***********/


        userSign.setBounds(100, 50, 200, 25);
        userSignInput.setBounds(100, 50, 200, 30);
        updateUserSignButton.setBounds(310, 50, 60, 30);
        userSignInput.setVisible(false);
        updateUserSignButton.setVisible(false);
        topPanel.add(userSign);
        topPanel.add(userSignInput);
        topPanel.add(updateUserSignButton);

/***********修改个性签名开始***********/
        userSign.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == e.BUTTON1) {
                    //鼠标左键事件， 让输入框聚焦
                    userSign.setVisible(false);
                    userSignInput.setText(userSign.getText());
                    userSignInput.setVisible(true);
                    updateUserSignButton.setVisible(true);
                    userSignInput.requestFocus();
                }
            }
        });
        updateUserSignButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateUserSignButton.setVisible(false);
                userSignInput.setVisible(false);
                userSign.setText(userSignInput.getText());
                userSign.setVisible(true);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("operation", "changeUserSign");
                jsonObject.put("userId", myId);
                jsonObject.put("newSign", userSign.getText());
                Client client = ClientSingleton.getClient();
                client.sendMessage(jsonObject.toString());
            }
        });
        userSignInput.addFocusListener(new FocusAdapter() {
            //失焦事件
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                updateUserSignButton.setVisible(false);
                userSignInput.setVisible(false);
                userSign.setVisible(true);
            }
        });
/***********修改个性签名结束***********/

        search.setBounds(0, 102, 400, 25);
        search.setBackground(new Color(210, 100, 225));
        search.setText("搜索功能没有实现，可以点击此处让修改签名和昵称失去焦点");
        frame.add(search);

        //messageList.add(new MessagePanel("ha", "jsdf", "we", "isdf", "234").getMessagePanel());

        messageScroll.setBounds(0, 0, 400, 400);
        messageList.setPreferredSize(new Dimension(400, 1000));
        messageScroll.setViewportView(messageList);

        groupMessage.add(messageScroll);
        tabbedPane.addTab("消息", groupMessage);
        tabbedPane.setBounds(0, 145, 400, 400);

        friendScroll.setBounds(0, 0, 410, 370);
        friendScroll.setHorizontalScrollBarPolicy(friendScroll.HORIZONTAL_SCROLLBAR_NEVER);  //水平滚动条从不出现

        groupFriend.add(friendScroll);
        tabbedPane.addTab("联系人", groupFriend);

        tree = new JTree(treeRoot);
        tree.setCellRenderer(new FriendNodeRenderer());

        //tree.setPreferredSize(new Dimension(380, 500));
        tree.addMouseListener(new MouseClickedTwiceListener());
        tree.setLayout(new FlowLayout());
        tree.setUI(new TreeUI());

        friendScroll.setViewportView(tree);
        frame.add(tabbedPane);

        messageListPanel = new Vector<>();  //vector是线程安全的  而ArrayList不是线程安全的

        addFriendButton.setBounds( 20, 560, 100, 30);
        addGroupButton.setBounds(140, 560, 100, 30);
        frame.add(addFriendButton);
        frame.add(addGroupButton);

        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFriendUI addFriendUI = UIFactory.getAddFriendUI();

            }
        });

        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputValue = JOptionPane.showInputDialog("请输入分组名称");
                System.out.println(inputValue);
                if(inputValue != null) {

                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
                    String t = sdf.format(d);

                    addGroup(new FriendNode(inputValue, myId + "_" + t));
                    Client client = ClientSingleton.getClient();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("operation", "addGroup");
                    Group group = new Group(myId + "_" + t, myId, inputValue);
                    jsonObject.put("group", group);
                    client.sendMessage(jsonObject.toString());
                }
            }
        });

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    /**
     * @Author wrp
     * @Description //TODO 设置主页面的个人信息
     * @Date 2020/5/13
     **/
    public void setMyInfo(String userName, String userSign, String userHeadImg, String userId) {
        this.myName = userName;
        this.mySign = userSign;
        this.myHeadImg = userHeadImg;
        this.myId = userId;

        this.userName.setText(userName);
        this.userSign.setText(userSign);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/head/" + userHeadImg + ".png"));
        icon.setImage((icon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        this.headImg.setIcon(icon);
    }

    /**
     * @Author wrp
     * @Description //TODO 添加分组
     * @Date 2020/5/13
     **/
    public void addGroup(FriendNode parent) {

        treeRoot.add(parent);
        tree.expandRow(0);
        tree.setRootVisible(false);
        tree.updateUI();

        //tree.addMouseListener(new MouseClickedTwiceListener());

        tree.setUI(new TreeUI());
    }

    public void addFriend(Friend friend) {
        //String group = friend.getFriendGroup();
        for (int i = 0; i < treeRoot.getLeafCount(); i++) {
            FriendNode t = (FriendNode) treeRoot.getChildAt(i);
            if (t.getGroupId().equals(friend.getFriendGroupId())) {
                t.add(new FriendNode(friend.getFriendHeadImg(), friend.getFriendName(),
                        friend.getFriendSign(), 1, friend.getFriendId(), friend.getFriendGroupId()));
                tree.updateUI();
                tree.setUI(new TreeUI());
                return;
            }
        }
        FriendNode newGroup = new FriendNode("temp", friend.getFriendGroupId());
        newGroup.add(new FriendNode(friend.getFriendHeadImg(), friend.getFriendName(),
                friend.getFriendSign(), 1, friend.getFriendId(), friend.getFriendGroupId()));
        addGroup(newGroup);
    }

    /**
     * @Author wrp
     * @Description //TODO 添加新的消息
     * @Date 2020/5/13
     **/
    public void addMessage(MessagePanel messagePanel) {

        for (int i = 0; i < messageListPanel.size(); i++) {
            if (messageListPanel.get(i).equals(messagePanel.getUserId())) {

                messageListPanel.remove(i);
                messageList.remove(i);
                break;
            }
        }
        messageListPanel.add(0, messagePanel);

        //更新布局
        messageList.removeAll();
        for (int i = 0; i < messageListPanel.size(); i++) {

            messageListPanel.get(i).setY(50 * i);
            messageList.add(messageListPanel.get(i).getMessagePanel());
        }
        messageList.setPreferredSize(new Dimension(380, messageListPanel.size() * 50 + 40));
        messageList.updateUI();
    }


    /**
     * @Author wrp
     * @Description //实现双击事件的监听
     * @Date 2020/5/27
     **/
    public static class MouseClickedTwiceListener extends MouseAdapter {
        private static boolean flag = false;        //双击事件已执行时置为真
        private static int clickNum = 1;        //指示鼠标点击次数，默认为单击

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            final MouseEvent me = e;
            if(me.isMetaDown()) {
                //鼠标右击
                //mouseRightClick();
                //if (SwingUtilities.isRightMouseButton(e)) {
                    mouseRightClick(me);
                //}
                return;
            }
            MouseClickedTwiceListener.flag = false;
            if (MouseClickedTwiceListener.clickNum == 2) {
                //鼠标点击次数为2调用双击事件
                this.mouseClickedTwice(me);
                //调用完毕clickNum置为1
                MouseClickedTwiceListener.clickNum = 1;
                MouseClickedTwiceListener.flag = true;
                return;
            }
            //新建定时器，双击检测间隔为500ms
            java.util.Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                //指示定时器执行次数
                int num = 0;

                @Override
                public void run() {
                    // 双击事件已经执行，取消定时器任务
                    if (MouseClickedTwiceListener.flag) {
                        num = 0;
                        MouseClickedTwiceListener.clickNum = 1;
                        this.cancel();
                        return;
                    }
                    //定时器再次执行，调用单击事件，然后取消定时器任务
                    if (num == 1) {
                        //mouseClickedOnce(me);
                        MouseClickedTwiceListener.flag = true;
                        MouseClickedTwiceListener.clickNum = 1;
                        num = 0;
                        this.cancel();
                        return;
                    }
                    clickNum++;
                    num++;
                }
            }, new Date(), 500);
        }


        private void mouseRightClick(MouseEvent e) {
            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            Rectangle pathBounds = tree.getUI().getPathBounds(tree, path);
            if (pathBounds != null && pathBounds.contains(e.getX(), e.getY())) {

                final FriendNode friendNode = (FriendNode) path.getLastPathComponent();
                System.out.println(friendNode);
                if (friendNode.getUserId() != null) {
                    //好友的位置点击右键
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem cancelFriend = new JMenuItem("删除好友");
                    JMenu moveFriend = new JMenu("移动好友至");
                    menu.add(cancelFriend);
                    cancelFriend.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //System.out.println("删除好友");

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("operation", "cancelFriend");
                            jsonObject.put("myId", myId);
                            jsonObject.put("friendId", friendNode.getUserId());
                            Client client = ClientSingleton.getClient();
                            client.sendMessage(jsonObject.toString());

                            FriendNode parent = (FriendNode) friendNode.getParent();
                            parent.remove(friendNode);

                            tree.updateUI();
                            tree.setUI(new TreeUI());
                        }
                    });
                    menu.add(moveFriend);

                    int count = treeRoot.getChildCount();
                    for(int i = 0; i < count; i++) {
                        final FriendNode t = (FriendNode) treeRoot.getChildAt(i);
                        final JMenuItem secondMenu = new JMenuItem(t.getUserName());
                        final int finalI = i;
                        secondMenu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println(finalI);
                                Friend goal = new Friend(friendNode.getUserName(), friendNode.getUserId(), friendNode.getUserSign(), friendNode.getHeadImage(), t.getGroupId());
                                FriendNode oldGroup = (FriendNode) friendNode.getParent();
                                oldGroup.remove(friendNode);
                                RqUI rqUI = UIFactory.getRqUI();
                                rqUI.addFriend(goal);

                                tree.updateUI();
                                tree.setUI(new TreeUI());

                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("operation", "changeGroup");
                                jsonObject.put("myId", myId);
                                jsonObject.put("friendId", friendNode.getUserId());
                                jsonObject.put("newGroupId", t.getGroupId());
                                Client client = ClientSingleton.getClient();
                                client.sendMessage(jsonObject.toString());
                            }
                        });
                        moveFriend.add(secondMenu);
                    }
                    menu.show(tree, e.getX(), e.getY());

                }
                else {
                    //分组位置右键
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem cancelGroup = new JMenuItem("删除分组");
                    menu.add(cancelGroup);
                    cancelGroup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //System.out.println("删除分组操作");
                            int count = treeRoot.getChildCount();
                            if(count == 1) {
                                //至少保留一个分组
                                JOptionPane.showMessageDialog(null, "至少保留一个分组", "提示",JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            int t = friendNode.getChildCount();
                            if(t != 0){
                                //该分组下的好友数，该分组有好友则不能删除
                                JOptionPane.showMessageDialog(null, "删除分组时该分组不能有好友", "提示",JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            treeRoot.remove(friendNode);
                            tree.updateUI();
                            tree.setUI(new TreeUI());

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("operation", "cancelGroup");
                            jsonObject.put("groupId", friendNode.getGroupId());

                            Client client = ClientSingleton.getClient();
                            client.sendMessage(jsonObject.toString());
                        }
                    });
                    menu.show(tree, e.getX(), e.getY());
                    tree.getLastSelectedPathComponent();


                }
            }
        }

        private void mouseClickedTwice(MouseEvent me) {
            // 双击事件
            FriendNode friendNode = (FriendNode) tree.getLastSelectedPathComponent();
            //System.out.println(friendNode);

            if (friendNode.getUserId() != null) {
                openDialog(friendNode.getUserId());

            }
        }

    }

    public static Map<String, DialogUI> getDialogBox() {

        return dialogBox;
    }

    public static String getMyId() {
        return myId;
    }

    public List<MessagePanel> getMessageListPanel() {
        return messageListPanel;
    }

    public static void openDialog(String friendId) {
        System.out.println("dialogBox" + dialogBox);
        if (!dialogBox.containsKey(friendId)) {
            //保证同一个好友只能打开一个窗口
            DialogUI dialogUI = new DialogUI(friendId, myId, myName, myHeadImg);
            dialogBox.put(friendId, dialogUI);

        }
    }

}
