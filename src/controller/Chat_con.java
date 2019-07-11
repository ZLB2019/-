package controller;

import com.sun.javafx.scene.control.skin.NestedTableColumnHeader;
import com.sun.security.ntlm.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import model.*;
import view.CLASS.windows_screen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

import static controller.FriendData_con.friend_id;
import static controller.Main_con.*;
import static model.ChatClient.*;
import static model.RefreshList.ChatWindows;


public class Chat_con implements Initializable {

    public int m;                                //当前聊天窗口的主人
    public int y;                                 //聊天对象
    @FXML
    private Label IdAndNet;

    @FXML
    private Button SendMessage;

    @FXML
    private ListView<MessageData> Chat;

    @FXML
    private TextArea Message;

    @FXML
    private ImageView QQshow;

    @FXML
    private MenuItem text1;

    @FXML
    private MenuItem text2;

    @FXML
    private MenuItem text3;

    @FXML
    private MenuItem text4;

    @FXML
    private Button SendImage;

    public ObservableList<MessageData> chat = FXCollections.observableArrayList();

    @FXML
    void text1_Action(ActionEvent event) {
        Message.setText(text1.getText());
    }

    @FXML
    void text2_Action(ActionEvent event) {
        Message.setText(text2.getText());
    }

    @FXML
    void text3_Action(ActionEvent event) {
        Message.setText(text3.getText());
    }

    @FXML
    void text4_Action(ActionEvent event) {
        Message.setText(text4.getText());
    }


    @FXML
    void SendImage_Action(ActionEvent event) throws  Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");			//打开文件窗口  名称
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Photo Files", "*.jpg", "*.png","*.bmp"));
        /**null———当前电脑显示器屏幕的中央。
         *this———当前你编写的程序屏幕中央
         * 如果是你其他的 控件名称 就是以这个 控件 为中心，弹出的文件选择器。
         **/
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String img = "file:///";
            img = img + file.getAbsolutePath();
            img = img.replace('\\', '/');
            MessageData messageData = new MessageData();
            messageData.setMain(m);
            messageData.setFriend(y);
            messageData.setMessage("");
            User user = AddDeleteCheckChange_friend.Select(id_main);
            messageData.setHead(user.getHeadPhoto());
            messageData.setTime(ReturnTime.ReturnYesTime());
            messageData.setImage(img);
            chat.add(messageData);
            send.SendMessage(img);
        }
    }

    public void AddData(MessageData messageData){
        chat.add(messageData);
    }

    @FXML
    void SendMessage_Action(ActionEvent event) throws Exception {


        if(Message.getText().equals(""))                //如果没有输入内容，不予发送
            return;

        MessageData m =new MessageData();
        m.setMain(Client_main);
        m.setFriend(Client_other);
        m.setMessage(Message.getText());
        m.setTime(ReturnTime.ReturnYesTime());
        User user = AddDeleteCheckChange_friend.Select(Client_main);
        m.setHead(user.getHeadPhoto());
        AddData(m);
        send.SendMessage(Message.getText());

        Message.setText("");                                            //清空输入框，代表已经发出

    }

    public void initialize(URL url, ResourceBundle rb) {

        ChatWindows.add(this);
        System.out.println(ChatWindows.size());

        m = id_main;
        y = friend_Edit;

        /**获得id和名字显示在label上*/
        try {
            String sql_FriendMessage = "select * from friendlist where Main="+id_main+" and Friend="+friend_Edit;
            ResultSet rs_Friendslist=null;
            rs_Friendslist= AddDeleteCheckChange_list.Selectlist(sql_FriendMessage);
            if(rs_Friendslist.next()) {
                IdAndNet.setText(friend_id + " ("+rs_Friendslist.getString("Note")+")");
            }
            User u =AddDeleteCheckChange_friend.Select(friend_Edit);
            if(u.getSex().equals("男")){
                Image img= new Image("file:///E:/聊天器图片素材/男版QQ秀.jpg");
                QQshow.setImage(img);
            }else{
                Image img= new Image("file:///E:/聊天器图片素材/女版QQ秀.jpg");
                QQshow.setImage(img);
            }

            AddDeleteCheckChange_list.MysqlClose();
        } catch (Exception e) {
            e.printStackTrace();
        }


        /**设置聊天界面的list*/
        Chat.setCellFactory(new Callback<ListView<MessageData>, ListCell<MessageData>>() {

            @Override
            public ListCell<MessageData> call(ListView<MessageData> param) {

                ListCell<MessageData> listcell  = new ListCell<MessageData>(){

                    @Override
                    //只定义编辑单元格一定要重写的方法
                    protected void updateItem(MessageData item, boolean empty) {

                        super.updateItem(item, empty);

                        if(!empty && item != null){
                            try {
                                if(item.getImage().equals("")) {
                                    HBox hbox = new HBox(10);
                                    User user = AddDeleteCheckChange_friend.Select(item.getMain());
                                    Image img = new Image(user.getHeadPhoto());
                                    ImageView Head = new ImageView();
                                    Head.setImage(img);
                                    Circle circle1 = new Circle();
                                    circle1.setRadius(25);
                                    circle1.setCenterX(25);
                                    circle1.setCenterY(25);
                                    Head.setClip(circle1);
                                    Head.setPreserveRatio(false);
                                    Head.setFitHeight(50);
                                    Head.setFitWidth(50);
                                    Label NetAndTimeAndMessage = new Label(user.getNetName() + "  " + item.getTime() + "\n" + item.getMessage());

                                    if (item.getMain() == id_main) {
                                        hbox.setAlignment(Pos.CENTER_RIGHT);
                                        hbox.getChildren().addAll(NetAndTimeAndMessage, Head);
                                    } else {
                                        hbox.setAlignment(Pos.CENTER_LEFT);
                                        hbox.getChildren().addAll(Head, NetAndTimeAndMessage);
                                    }
                                    this.setGraphic(hbox);
                                }else{
                                    HBox hbox = new HBox(10);
                                    User user = AddDeleteCheckChange_friend.Select(item.getMain());
                                    Image img = new Image(user.getHeadPhoto());
                                    ImageView Head = new ImageView();
                                    Head.setImage(img);
                                    Circle circle1 = new Circle();
                                    circle1.setRadius(25);
                                    circle1.setCenterX(25);
                                    circle1.setCenterY(25);
                                    Head.setClip(circle1);
                                    Head.setPreserveRatio(false);
                                    Head.setFitHeight(50);
                                    Head.setFitWidth(50);
                                    ImageView image = new ImageView();
                                    image.setImage(new Image(item.getImage()));
                                    image.setPreserveRatio(true);
                                    image.setFitHeight(200);
                                    Label NetAndTimeAndMessage = new Label(user.getNetName() + "  " + item.getTime() + "\n");
                                    VBox vbox = new VBox(10);
                                    if (item.getMain() == id_main) {
                                        hbox.setAlignment(Pos.CENTER_RIGHT);
                                        hbox.getChildren().addAll(NetAndTimeAndMessage, Head);
                                        vbox.getChildren().addAll(hbox,image);
                                        vbox.setAlignment(Pos.CENTER_RIGHT);
                                    } else {
                                        hbox.setAlignment(Pos.CENTER_LEFT);
                                        hbox.getChildren().addAll(Head, NetAndTimeAndMessage);
                                        vbox.getChildren().addAll(hbox,image);
                                        vbox.setAlignment(Pos.CENTER_LEFT);
                                    }
                                    this.setGraphic(vbox);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if(empty){
                            setText(null);
                            setGraphic(null);
                        }
                    }

                };
                return listcell;
            }
        });

        /**弄聊天的显示*/
        try {
            Chat.setItems(chat);
            chat.clear();
            String sql_chat = "select * from chat where (Main="+ Client_main+" and Friend="+Client_other+") or (Main="+ Client_other+" and Friend="+Client_main+")";
            ResultSet rs_chat=null;
            rs_chat= AddDeleteCheckChange_list.Selectlist(sql_chat);
            while(rs_chat.next()) {
                MessageData messageData = new MessageData();
                User user = new User();
                user = AddDeleteCheckChange_friend.Select(rs_chat.getInt("Main"));

                messageData.setMain(rs_chat.getInt("Main"));                               //获得id
                messageData.setHead(user.getHeadPhoto());                                             //获得头像地址
                messageData.setMessage(rs_chat.getString("Message"));                   //获取消息
                //获取时间
                messageData.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs_chat.getTimestamp("Time")));
                messageData.setImage(rs_chat.getString("Image"));
                chat.add(messageData);
            }
            AddDeleteCheckChange_list.MysqlClose();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
