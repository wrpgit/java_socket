package com.wrpxcx.serverSocket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: wrp
 * @TODO: serverSocket的启动类
 * @time: 2020-05-15 19:40
 **/
public class MainSocket extends Thread{
    private static ServerSocket serverSocket;


    public static void main(String[] args) {
        MainSocket mainSocket = new MainSocket();
        mainSocket.start();

    }

    static {
        try {
            serverSocket = new ServerSocket(8081);
            //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("8081 启动成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                MySocket t = new MySocket(socket);
                t.init();
                System.out.println("连接到socket" + socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
