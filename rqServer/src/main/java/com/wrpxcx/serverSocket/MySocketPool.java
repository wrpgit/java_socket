package com.wrpxcx.serverSocket;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wrp
 * @TODO: 存储每个以登陆的socket, 对应用户Id
 * @time: 2020-05-15 19:05
 **/
public class MySocketPool {
    public static Map<String, MySocket> mapSocket = new HashMap();

    public static void addUser(String userId, MySocket mySocket) {

        //System.out.println("用户" + userId + "加入连接池");
        mapSocket.put(userId, mySocket);
    }
    public static void removeUser(String userId) {

        //System.out.println("用户" + userId + "移出连接池");
        mapSocket.remove(userId);
    }

    public static MySocket getUserSocketById(String userId) {

        return mapSocket.get(userId);
    }


}
