package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import model.*;
import view.CLASS.windows_screen;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static controller.Main_con.*;
import static model.ChatClient.Client_main;
import static model.ChatClient.Client_other;
import static model.RefreshList.ChatWindows;
import static model.RefreshList.GroupChatWindows;

public class GroupChat_con implements Initializable {

    @FXML
    private TextArea Message;

    @FXML
    private ListView<MessageData> Chat;

    @FXML
    private Label IdAndNet;

    @FXML
    private Button SendMessage;

    @FXML
    private ListView<Data> GroupUserList;

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

    @FXML
    private Button AddGroupUser;

    @FXML
    private TextField FriendFind;

    @FXML
    private ImageView Head;

    public int GroupId;

    public ObservableList<MessageData> chat = FXCollections.observableArrayList();                   //群内消息记录
    public ObservableList<Data> GroupUserlist = FXCollections.observableArrayList();             //群内用户列表

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
    void SendImage_Action(ActionEvent event) throws Exception{
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
            messageData.setMain(id_main);
            messageData.setFriend(GroupId);
            messageData.setMessage("");
            User user = AddDeleteCheckChange_friend.Select(id_main);
            messageData.setHead(user.getHeadPhoto());
            messageData.setTime(ReturnTime.ReturnYesTime());
            messageData.setImage(img);
            chat.add(messageData);
            send.SendMessage(img);
        }

    }

    @FXML
    void AddGroupUser_Action(ActionEvent event) {
        if(FriendFind.getText().equals(""))
            return;
        try {
            User user = AddDeleteCheckChange_friend.Select(Integer.parseInt(FriendFind.getText()));
            if(user.getId()==0){
                /**没有查找到*/
                new windows_screen(). NewWindows(new Stage(),"../FXML/FindError.fxml","查找失败",392,210);
            }else{
                /**找到了*/
                String sql = "select * from grouplist where Main_id="+Integer.parseInt(FriendFind.getText())+" and Group_id="+GroupId;
                System.out.println(sql);
                ResultSet rs;
                rs = AddDeleteCheckChange_list.Selectlist(sql);
                if(rs.next()){              //这个人加了群
                    //这个人在群内
                    new windows_screen(). NewWindows(new Stage(),"../FXML/InvitationError.fxml","邀请群成员失败",392,210);
                }else{
                    //邀请成功
                    new windows_screen(). NewWindows(new Stage(),"../FXML/InvitationSuccessful.fxml","邀请群成员成功",392,210);
                    String sql_GroupAddUser = "insert into grouplist(Main_id,Group_id)"
                                             +" values("+Integer.parseInt(FriendFind.getText())+","+GroupId+")";
                    AddDeleteCheckChange_Group.Insert(sql_GroupAddUser);
                    Data data = new Data();
                    data.setHead(user.getHeadPhoto());
                    data.setNote(user.getNetName());
                    GroupUserlist.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddData(MessageData messageData){
        chat.add(messageData);
    }

    @FXML
    void SendMessage_Action(ActionEvent event) throws Exception{
        if(Message.getText().equals(""))                //如果没有输入内容，不予发送
            return;
        MessageData m =new MessageData();
        m.setMain(Client_main);
        m.setFriend(Client_other);
        m.setMessage(Message.getText());
        m.setTime(ReturnTime.ReturnYesTime());
        User user = AddDeleteCheckChange_friend.Select(Client_main);
        m.setHead(user.getHeadPhoto());
        chat.add(m);
        send.SendMessage(Message.getText());

        Message.setText("");                                            //清空输入框，代表已经发出


    }

    public void initialize(URL url, ResourceBundle rb) {

        GroupId = Client_other;
        GroupChatWindows.add(this);

        /**消息列表*/
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


        /**群内用户列表*/
        GroupUserList.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {

            @Override
            public ListCell<Data> call(ListView<Data> param) {
                // TODO Auto-generated method stub
                ListCell<Data> listcell  = new ListCell<Data>(){


                    @Override
                    //只定义编辑单元格一定要重写的方法
                    protected void updateItem(Data item, boolean empty) {
                        // TODO Auto-generated method stub
                        super.updateItem(item, empty);

                        if(!empty && item != null){
                            HBox hbox = new HBox(10);
                            hbox.setAlignment(Pos.CENTER_LEFT);

                            Image img = new Image(item.getHead());
                            ImageView Head = new ImageView();
                            Head.setImage(img);
                            Head.setPreserveRatio(true);
                            Head.setFitHeight(30);
                            Circle circle1 = new Circle( );
                            circle1.setRadius(15);
                            circle1.setCenterX(15);
                            circle1.setCenterY(15);
                            Head.setClip(circle1);

                            Label Note =new Label(item.getNote());
                            hbox.getChildren().addAll(Head,Note);
                            this.setGraphic(hbox);
                        }else if(empty){
                            setText(null);
                            setGraphic(null);
                        }
                    }

                };
                return listcell;
            }
        });

        try {
            Group GroupMessage = new Group();
            String sql_Group = "select * from groupmanagement where Group_id="+group_Edit;
            GroupMessage= AddDeleteCheckChange_Group.Select_Group(sql_Group);

            Image img = new Image(GroupMessage.getGroup_head());
            Head.setImage(img);                                                           //群头像
            Circle circle1 = new Circle( );
            circle1.setRadius(100);
            circle1.setCenterX(100);
            circle1.setCenterY(100);
            Head.setClip(circle1);

            IdAndNet.setText(GroupMessage.getGroup_id()+" ("+GroupMessage.getGroup_name()+")");                                   //群名称

            GroupUserList.setItems(GroupUserlist);
            GroupUserlist.clear();
            String sql_Grouplist = "select * from grouplist where Group_id="+group_Edit;
            ResultSet rs_GroupUserlist=null;
            rs_GroupUserlist= AddDeleteCheckChange_list.Selectlist(sql_Grouplist);
            while(rs_GroupUserlist.next()) {
                Data data = new Data();
                User user = AddDeleteCheckChange_friend.Select(rs_GroupUserlist.getInt("Main_id"));
                data.setHead(user.getHeadPhoto());
                data.setNote(user.getNetName());
                GroupUserlist.add(data);
            }
            AddDeleteCheckChange_list.MysqlClose();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /**弄聊天的显示*/
        try {
            Chat.setItems(chat);
            chat.clear();
            String sql_chat = "select * from chat where Friend="+Client_other;
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
