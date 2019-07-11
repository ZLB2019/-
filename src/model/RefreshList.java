package model;

import com.sun.javafx.image.BytePixelSetter;
import controller.Chat_con;
import controller.GroupChat_con;
import javafx.application.Platform;

import javax.swing.*;
import java.util.ArrayList;

public class RefreshList {

    public static ArrayList<Chat_con> ChatWindows = new ArrayList<>();
    public static ArrayList<GroupChat_con> GroupChatWindows = new ArrayList<>();

    public static void AddList(MessageData messageData) {
        Platform.runLater(() -> {
            if (messageData.getFriend() > 10000000)                     //私聊
                for (Chat_con list : ChatWindows) {
                    if ((list.m == messageData.getFriend() && list.y == messageData.getMain())) {
                        list.AddData(messageData);
                    }
                }
            else
                for (GroupChat_con list : GroupChatWindows) {
                    if (list.GroupId == messageData.getFriend()) {      //群聊
                        list.AddData(messageData);
                    }
                }

        });

    }
}
