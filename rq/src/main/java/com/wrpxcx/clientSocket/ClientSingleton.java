package com.wrpxcx.clientSocket;

public class ClientSingleton {

    private static final Client client = new Client();

    static {
        client.init();
    }
    /**
     * @Author wrp
     * @Description //TODO 始终保持是一个socket对象，每次发送消息或接收消息都从次来
     * @Date  2020/5/10
     **/
    public static Client getClient() {

        return client;
    }
}
