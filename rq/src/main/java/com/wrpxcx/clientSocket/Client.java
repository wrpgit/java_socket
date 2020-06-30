package com.wrpxcx.clientSocket;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


/**
 * @Author wrp
 * @Description //TODO 客户端的socket对象存储，提供接收消息和发送消息的方法
 * @Date  2020/5/10
 **/
public class Client {

    private Socket socket;
    private PrintWriter out;  //输出流
    private BufferedWriter bw;
    private BufferedReader br;  //输入流

    public void init() {
        try {
            socket = new Socket("localhost", 8081);
            //socket = new Socket("www.wrpxcx.com", 8081);
            out = new PrintWriter(socket.getOutputStream());
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            new ReceiveThread(br).start();

            new Heart(socket, out).start();

        } catch (IOException e) {
            System.out.println("socket连接失败");
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {

        System.out.println("客户端发送了：" + message);
        message.replace('\n', (char)7);   //把换行替换成7， 在服务器接收后把7变成换行
        message += "\n";

        new SendThread(bw, message).start();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.init();
    }
}
