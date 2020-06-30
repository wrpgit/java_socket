package com.wrpxcx.clientSocket;

import com.alibaba.fastjson.JSONObject;

import java.io.PrintWriter;
import java.net.Socket;


/**
 * @Author wrp
 * @Description 发送心跳包线程，保持连接
 * @Date  2020/5/10
 **/
public class Heart extends Thread{

    private Socket socket;
    private PrintWriter out;


    public Heart(Socket socket, PrintWriter out) {

        System.out.println("心跳包线程启动成功");
        this.socket = socket;
        this.out = out;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            try {
                Thread.sleep(120 * 1000);
                System.out.println("发送了一个心跳包");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("operation", "HeartPacket");
                out.write(jsonObject.toJSONString() + "\n");
                out.flush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
