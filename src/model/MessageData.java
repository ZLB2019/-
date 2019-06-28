package model;

import java.util.IdentityHashMap;

public class MessageData {
    private int Main;
    private int Friend;
    private String Message;
    private String Time;
    private String Head;

    public void setHead(String head) {
        Head = head;
    }

    public String getHead() {
        return Head;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setMain(int main) {
        Main = main;
    }

    public void setFriend(int friend) {
        Friend = friend;
    }

    public int getMain() {
        return Main;
    }

    public int getFriend() {
        return Friend;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMessage() {
        return Message;
    }

    public String getTime() {
        return Time;
    }
}
