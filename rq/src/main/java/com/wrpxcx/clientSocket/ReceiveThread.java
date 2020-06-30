package com.wrpxcx.clientSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wrpxcx.entity.DialogMessage;
import com.wrpxcx.entity.LastMessage;
import com.wrpxcx.entity.User;
import com.wrpxcx.swingUi.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author wrp
 * @Description //TODO 接收消息的线程，将接受的消息根据需求分发到不同的界面
 * @Date  2020/5/10
 **/
public class ReceiveThread extends Thread{

    private BufferedReader br;

    public ReceiveThread(BufferedReader br) {

        this.br = br;
    }

    @Override
    public void run() {
        super.run();

        while(true) {

            String response = null;
            try {
                if((response = br.readLine()) != null) {

                    response.replace((char)7, '\n');  //规定发消息前将换行换成了7， 将其换回来

                    System.out.println("客户端收到的信息如下： " + response);
                    JSONObject jsonObject = JSON.parseObject(response);
                    String operation = jsonObject.getString("operation");
                    if(operation.equals("loginBack")){
                        LoginUI t = UIFactory.getLoginUI();
                        t.dealLoginBack(jsonObject);
                    }
                    else if(operation.equals("dialogInfo")) {
                        User friendInfo = jsonObject.getObject("friendInfo", User.class);

                        DialogUI dialogUI = RqUI.getDialogBox().get(friendInfo.getUserId());
                        //如果窗口没关闭
                        if(dialogUI != null) {
                            dialogUI.setFriendInfo(friendInfo.getUserName(), friendInfo.getUserHeadImg());
                            //List<LastMessage> lastMessages = jsonObject.getObject("lastMessage", new TypeReference<List<LastMessage>>(){});
                            List<DialogMessage> dialogMessages = jsonObject.getObject("dialogMessageList", new TypeReference<List<DialogMessage>>(){});

                            for (DialogMessage t : dialogMessages) {

                                dialogUI.addMessage(t);
                            }
                        }
                    }
                    else if(operation.equals("sendMessage")) {
                        //收到好友发送的消息
                        DialogMessage message = jsonObject.getObject("message", DialogMessage.class);
                        DialogUI dialogUI = RqUI.getDialogBox().get(message.getFromId());
                        if(dialogUI != null) {
                            dialogUI.addMessage(message);
                        }
                    }
                    else if(operation.equals("updateMessageList")) {
                        List<LastMessage> lastMessages = jsonObject.getObject("lastMessage", new TypeReference<List<LastMessage>>(){});

                        RqUI rqUI = UIFactory.getRqUI();
                        rqUI.getMessageListPanel().clear();
                        //rqUI.getMessageListPanel().removeAll();
                        for(LastMessage t : lastMessages) {
                            //System.out.println(t);
                            if(t.getFromId().equals(RqUI.getMyId())) {
                                //我是发送者
                                rqUI.addMessage(new MessagePanel(t.getToName(), t.getLastMessage(), t.getLastTime(), t.getToHeadImg(), t.getToId()));
                            }
                            else{
                                //我是接收者
                                rqUI.addMessage(new MessagePanel(t.getFromName(), t.getLastMessage(), t.getLastTime(), t.getFromHeadImg(), t.getFromId()));
                            }

                        }
                    }
                    else if(operation.equals("searchResult")) {
                        AddFriendUI addFriendUI = UIFactory.getAddFriendUI();
                        addFriendUI.dealSearchResult(jsonObject);

                    }
                    else if(operation.equals("addFriendResult")) {
                        AddFriendUI addFriendUI = UIFactory.getAddFriendUI();
                        addFriendUI.dealAddFriendResult(jsonObject);
                    }

                    else if(operation.equals("signUpReturn")) {
                        SignUpUI signUpUI = UIFactory.getSignUpUI();
                        signUpUI.signUpReturn(jsonObject);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("连接断开");
                break;
            }

        }
    }
}
