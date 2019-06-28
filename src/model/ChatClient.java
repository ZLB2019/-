package model;


import java.io.*;
import java.net.*;
import java.util.Calendar;

import static model.RefreshList.ChatWindows;


public class ChatClient {

    public static int Client_main;
    public static int Client_friend;

    public int name;

    private  Socket client = null;

    /**连接服务器*/
    public SendMessageToServer RunClient(int id) throws Exception {

        client = new Socket("localhost",8888);                                  //连接服务器端
        name = id;
        SendMessageToServer sendMessageToServer = new SendMessageToServer(client,id);
        new Thread(sendMessageToServer).start();                                //多线程，一直等待接收消息
        return sendMessageToServer;
    }

    /**关闭客户端*/
    public void ClientClose(Socket client) throws IOException{
        client.close();
    }

}
