package model;

import com.sun.security.ntlm.Server;
import controller.chat_con;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;


public class ChatServer {

    private static CopyOnWriteArrayList<channel> all = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception{
        ServerSocket server = null;								//声明ServerSocket对象
        Socket client = null;									//一个Socket对象表示一个客户端



        server = new ServerSocket(8888);						//此时服务器在8888端口上等待客户端的访问
        System.out.println("服务器运行，等待客户端连接");
        boolean isrunning = true;
        while(isrunning)
        {
            client = server.accept();								//程序堵塞，等待客户端连接
            channel c = new channel(client);
            all.add(c);         //管理所有成员
            new Thread(c).start();
        }

        server.close();

    }

    static class channel implements Runnable
    {
        private Socket client = null;
        private BufferedReader bufr = null;
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private InputStream input = null;
        private int name;
        private boolean isrunning;
        //构造器
        public channel(Socket client) {
            this.client=client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                isrunning = true;
                name =Integer.parseInt(dis.readUTF());      //获得用户名
                System.out.println("用户"+name+"已经登录Chat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**转发*/
        private void SendOther(String msg,int OthersId){
            System.out.println("目前已经连接的客户端有"+all.size()+"个");
            System.out.println("要发送的对象是"+OthersId);
            if(OthersId>10000000)   /**私聊*/
            {
                for(channel others : all){
                    System.out.println(others.name);
                    if(others.name == OthersId)
                        Send(others, msg);
                }
            }
            else                    /**群聊*/
            {

            }

        }

        @Override
        public void run() {
            String str = "";
            String flag = "";
            while(isrunning)
            {
                try {
                    dis = new DataInputStream(client.getInputStream());
                    str=dis.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(str.equals(null)) break;

                System.out.println(str);

                String[] UandM = str.split("Send");
                String[] MandF = UandM[0].split("->");
                String main_id = MandF[0];
                String friend_id = MandF[1];
                String message = UandM[1];

                System.out.println("发件人:");
                System.out.println(main_id);
                System.out.println("收件人:");
                System.out.println(friend_id);
                System.out.println("信息内容:");
                System.out.println(message);

                //时间
                String timeString = ReturnTime.ReturnNoTime();

                //聊天记录存入数据库
                try {
                    ChatRecord.mysqlc(main_id, friend_id, message, timeString);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //转发给对面
                SendOther(str,Integer.parseInt(friend_id));

                str = null;

            }
            try {
                client.close();					//关闭客户端
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //发送消息到客户端
        public void Send(channel c, String msg)
        {
            try {
                DataOutputStream dosSend = new DataOutputStream(c.client.getOutputStream());
                dosSend.writeUTF(msg);
                dosSend.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
