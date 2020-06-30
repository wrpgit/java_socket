package com.wrpxcx.serverSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.wrpxcx.entity.*;
import com.wrpxcx.mapper.FriendMapper;
import com.wrpxcx.mapper.MessageMapper;
import com.wrpxcx.mapper.UserMapper;
import com.wrpxcx.util.SpringUtil;
import netscape.javascript.JSObject;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Random;

/**
 * @author: wrp
 * @TODO: 保存socket对象
 * @time: 2020-05-15 19:10
 **/
public class MySocket {
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;

    private String userId;

    public MySocket(Socket socket) {

        this.socket = socket;
    }

    public void init() {

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //监听客户端的消息
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    String request = null;
                    try {
                        if ((request = br.readLine()) != null) {

                            request.replace((char) 7, '\n');
                            System.out.println("收到了信息如下： " + request);
                            JSONObject jsonObject = JSON.parseObject(request);
                            String operation = jsonObject.getString("operation");
                            if (operation.equals("login")) {
                                //登陆操作
                                dealLogin(jsonObject);
                            } else if (operation.equals("dialogInfo")) {
                                //消息框信息
                                returnDialogInfo(jsonObject);
                            } else if (operation.equals("sendMessage")) {
                                //收发聊天消息
                                sendMessage(jsonObject);
                            }
                            else if(operation.equals("addGroup")) {
                                //添加新的分组
                                addGroup(jsonObject);
                            }
                            else if(operation.equals("searchUser")) {
                                //添加好友时的 查找
                                searchUser(jsonObject);
                            }
                            else if(operation.equals("addFriend")) {
                                //添加好友
                                addFriend(jsonObject);
                            }
                            else if(operation.equals("signUp")) {
                                //注册
                                signUp(jsonObject);
                            }
                            else if(operation.equals("changeGroup")) {
                                //修改好友分组
                                changeFriendGroup(jsonObject);
                            }
                            else if(operation.equals("cancelGroup")) {
                                //删除分组
                                cancelGroup(jsonObject);
                            }
                            else if(operation.equals("cancelFriend")) {
                                //删除好友
                                cancelFriend(jsonObject);
                            }
                            else if(operation.equals("changeUserSign")){
                                //更新个性签名
                                changeUserSign(jsonObject);
                            }
                            else if(operation.equals("changeUserName")) {
                                //修改昵称
                                changeUserName(jsonObject);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("连接断开");
                        MySocketPool.removeUser(userId);
                        break;
                    }
                }
            }
        }).start();
    }

    public void sendToClient(String data) {

        System.out.println("服务端发送了如下信息： " + data);
        data.replace('\n', (char)7);
        try {
            bw.write(data + '\n');
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dealLogin(JSONObject jsonObject) {
        //处理登陆请求，
        String userId = jsonObject.getString("userId");
        String password = jsonObject.getString("password");
//        System.out.println("收到的userId为： " + userId);
//        System.out.println("收到的password为： " + password);
        JSONObject res = new JSONObject();
        res.put("operation", "loginBack");

        UserMapper userMapper = (UserMapper) SpringUtil.getBean("userMapper");
        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        MessageMapper messageMapper = (MessageMapper) SpringUtil.getBean("messageMapper");
        if( 1 == userMapper.checkUser(userId, password)){

            List<Group> groups = friendMapper.getGroupByUserId(userId);
            res.put("groups", groups);

            List<FriendDetail> friendDetails = friendMapper.getFriendById(userId);
            res.put("friends", friendDetails);

            User userInfo = userMapper.getUserById(userId);
            res.put("userInfo", userInfo);

            List<LastMessageDetail> lastMessageDetails = messageMapper.getMessageListById(userId);
            res.put("lastMessage", lastMessageDetails);

            System.out.println("密码正确");

            MySocketPool.addUser(userId, this);
            this.userId = userId;
            res.put("status", 200);
        }else {
            System.out.println("账号或密码不正确");
            res.put("status", 1);
        }
        sendToClient(res.toJSONString());
    }

    public void returnDialogInfo(JSONObject jsonObject) {
        String friendId = jsonObject.getString("friendId");
        String myId = jsonObject.getString("myId");
        UserMapper userMapper = (UserMapper) SpringUtil.getBean("userMapper");
        MessageMapper messageMapper = (MessageMapper) SpringUtil.getBean("messageMapper");

        User friendInfo = userMapper.getUserById(friendId);
        List<Message> messages = messageMapper.getMessagesByUserId(friendId, myId);

        JSONObject res = new JSONObject();
        res.put("operation", "dialogInfo");
        res.put("friendInfo", friendInfo);
        res.put("dialogMessageList", messages);
        sendToClient(res.toJSONString());

    }

    public void sendMessage(JSONObject jsonObject) {

        Message message = jsonObject.getObject("message", Message.class);
        MessageMapper messageMapper = (MessageMapper) SpringUtil.getBean("messageMapper");
        messageMapper.addMessage(message);  //将消息添加的数据库中

        LastMessage lastMessage = messageMapper.getMessageListByFromAndTo(message.getFromId(), message.getToId());
        if(lastMessage == null) {
            //首次通信，添加到消息列表中
            lastMessage = new LastMessage(message.getFromId(), message.getToId(), message.getMessage(),message.getMessageTime(), message.getStatus());
            messageMapper.addLastMessage(lastMessage);
        }
        else {
            //更新最后一条消息
            lastMessage = null;  //gc
            LastMessage t = new LastMessage(message.getFromId(), message.getToId(), message.getMessage(),message.getMessageTime(), message.getStatus());
            messageMapper.updateLastMessage(t);
        }
        // 如果目标用户在线， 发送给该用户
        MySocket friendSocket = MySocketPool.getUserSocketById(message.getToId());
        if(friendSocket != null) {
            JSONObject ans = new JSONObject();
            ans.put("operation", "sendMessage");
            ans.put("message", message);
            friendSocket.sendToClient(ans.toJSONString());

            //更新消息列表
            JSONObject ans2 = new JSONObject();
            ans2.put("operation", "updateMessageList");
            List<LastMessageDetail> lastMessageDetails = messageMapper.getMessageListById(message.getToId());
            ans2.put("lastMessage", lastMessageDetails);
            friendSocket.sendToClient(ans2.toJSONString());
        }

        MySocket mySocket = MySocketPool.getUserSocketById(message.getFromId());
        if(mySocket != null){

            JSONObject ans2 = new JSONObject();
            ans2.put("operation", "updateMessageList");
            List<LastMessageDetail> lastMessageDetails = messageMapper.getMessageListById(userId);
            ans2.put("lastMessage", lastMessageDetails);
            mySocket.sendToClient(ans2.toJSONString());
        }
    }

    public void addGroup(JSONObject jsonObject) {
        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        Group group = jsonObject.getObject("group", Group.class);
        friendMapper.addGroup(group);
    }

    public void searchUser(JSONObject jsonObject) {
        UserMapper userMapper = (UserMapper) SpringUtil.getBean("userMapper");
        User user = userMapper.getUserById(jsonObject.getString("userId"));
        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        JSONObject res = new JSONObject();
        res.put("operation", "searchResult");
        if(user == null) {
            res.put("status", 0);
        }
        else {
            res.put("status", 200);
            res.put("userInfo", user);
            List<Group> groups = friendMapper.getGroupByUserId(jsonObject.getString("myId"));
            res.put("group", groups);
        }
        MySocket mySocket = MySocketPool.getUserSocketById(jsonObject.getString("myId"));
        if(mySocket != null) {
            mySocket.sendToClient(res.toString());
        }
    }

    public void addFriend(JSONObject jsonObject) {
        String friendId = jsonObject.getString("friendId");
        String myId = jsonObject.getString("myId");
        String groupId = jsonObject.getString("groupId");

        JSONObject res = new JSONObject();
        res.put("operation", "addFriendResult");

        Friend friend = new Friend(myId, friendId, groupId);
        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        int t = friendMapper.checkFriend(friend);
        if(t >= 1) {
            //已经是好友了
            res.put("status", 1);
        } else{
            friendMapper.addFriend(friend);
            res.put("status", 200);
            FriendDetail friendDetail = friendMapper.getFriendByMyIdAndFriendId(myId, friendId);
            res.put("friend", friendDetail);
        }
        MySocket mySocket = MySocketPool.getUserSocketById(jsonObject.getString("myId"));
        if(mySocket != null) {
            mySocket.sendToClient(res.toString());
        }
    }
    public void signUp(JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        String userSing = jsonObject.getString("userSign");

        UserMapper userMapper = (UserMapper) SpringUtil.getBean("userMapper");
        Random r = new Random();
        int headInt = r.nextInt(15);
        String userId = "1";
        while (true) {
            //随机生成RQ号
            userId = "1";
            for (int i = 0; i < 4; i++) {
                int t = r.nextInt(10);
                userId += t;
            }
            User user = userMapper.getUserById(userId);
            if (user == null) {
                break;
            }
        }
        User user = new User(userId, userName, userSing, Integer.toString(headInt));

        userMapper.addUser(user);
        userMapper.addTbLogin(userId, password);

        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        Group group = new Group(userId, userId, "我的好友");
        friendMapper.addGroup(group);

        JSONObject res = new JSONObject();
        res.put("operation", "signUpReturn");
        res.put("userId", userId);
        sendToClient(res.toString());
    }

    public void cancelFriend(JSONObject jsonObject) {
        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        String myId = jsonObject.getString("myId");
        String friendId = jsonObject.getString("friendId");

        friendMapper.removeFriend(myId, friendId);
    }

    public void cancelGroup(JSONObject jsonObject) {
        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        String groupId = jsonObject.getString("groupId");
        friendMapper.removeGroup(groupId);
    }

    public void changeFriendGroup(JSONObject jsonObject) {
        FriendMapper friendMapper = (FriendMapper) SpringUtil.getBean("friendMapper");
        String myId = jsonObject.getString("myId");
        String friendId = jsonObject.getString("friendId");
        String newGroupId = jsonObject.getString("newGroupId");
        friendMapper.changeFriendGroup(new Friend(myId,friendId,newGroupId));
    }

    public void changeUserSign(JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String newSign = jsonObject.getString("newSign");
        UserMapper userMapper = (UserMapper) SpringUtil.getBean("userMapper");
        User user = new User(userId, null, newSign, null);
        userMapper.updateUser(user);
    }

    public void changeUserName(JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String newName = jsonObject.getString("newName");
        UserMapper userMapper = (UserMapper) SpringUtil.getBean("userMapper");
        User user = new User(userId, newName, null, null);
        userMapper.updateUser(user);
    }
}
