package com.wrpxcx.clientSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author wrp
 * @Description //TODO 为服务器发送消息的线程
 * @Date  2020/5/10
 **/
public class SendThread extends Thread{

    private BufferedWriter bw;
    private String message;

    public SendThread(BufferedWriter bw, String message) {
        this.bw = bw;
        this.message = message;
    }

    @Override
    public void run() {
        super.run();
        try {
            bw.write(message);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
