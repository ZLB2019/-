package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static model.ChatClient.Client_main;
import static model.ChatClient.Client_other;
import static model.RefreshList.ChatWindows;

public class SendMessageToServer implements Runnable{

    private Socket client;
    private int name;
    private boolean isrunning = true;
    private String messageString = null;

    private  DataOutputStream dos = null;
    private DataInputStream dis = null;
    private MessageData messageData;

    //构造器
    public SendMessageToServer(Socket client,int id){
        this.client = client;
        this.name = id;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
            Send(id+"");                        //告诉服务器连接客户端的用户名
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(isrunning)
        {
            try {
                messageString = dis.readUTF();


                String[] UandM = messageString.split("Send");
                String[] MandF = UandM[0].split("->");
                String main_id = MandF[0];
                String friend_id = MandF[1];
                String message = "";
                String img = "";
                if(UandM[1].contains(".jpg")||UandM[1].contains(".png")||UandM[1].contains(".bmp")||UandM[1].contains(".JPG")||UandM[1].contains(".PNG")||UandM[1].contains(".BMP")){
                    img = UandM[1];
                }else{
                    message = UandM[1];
                }
                messageData = new MessageData();
                messageData.setImage(img);
                messageData.setMain(Integer.parseInt(main_id));
                messageData.setFriend(Integer.parseInt(friend_id));
                messageData.setMessage(message);
                messageData.setTime(ReturnTime.ReturnYesTime());
                User user = AddDeleteCheckChange_friend.Select(Client_main);
                messageData.setHead(user.getHeadPhoto());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            RefreshList.AddList(messageData);
            System.out.println("用户"+Client_main+"收到了消息："+messageString);
        }
    }

    /**发送消息给服务器*/
    public  void SendMessage(String Client_message) throws IOException {
        String string = Client_main+"->"+Client_other+"Send"+Client_message;

//        messageData.setMain(Client_main);
//        messageData.setFriend(Client_friend);
//        messageData.setMessage(Client_message);
//        messageData.setTime(ReturnTime.ReturnYesTime());
//        try {
//            User user = AddDeleteCheckChange_friend.Select(Client_main);
//            messageData.setHead(user.getHeadPhoto());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

       // RefreshList.AddList(messageData);           //添加消息到listview


        dos.writeUTF(string);
    }



    //发送消息
    public void Send(String msg)
    {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
