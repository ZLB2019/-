package model;

import controller.chat_con;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.CheckedOutputStream;

import static model.ChatClient.Client_friend;
import static model.ChatClient.Client_main;

public class RefreshList {

    public static ArrayList<chat_con> ChatWindows = new ArrayList<>();

    public static void AddList(MessageData messageData){
        System.out.println("进来了,有"+ChatWindows.size()+"个ChatWindows");
            for (chat_con list : ChatWindows) {
                if( (list.m == messageData.getMain()&&list.y == messageData.getFriend()) || (list.m == messageData.getFriend()&&list.y == messageData.getMain()) ){
                    Platform.runLater(()->{
                       list.AddData(messageData);
                    });
                }
            }
    }

}
