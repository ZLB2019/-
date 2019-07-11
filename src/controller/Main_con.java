package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import view.CLASS.windows_screen;

import java.net.Socket;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class Main_con implements Initializable {


    @FXML
    private TextField GroupFind;

    @FXML
    private TextField FriendFind;

    @FXML
    private ListView<Data> MessageList;

    @FXML
    private ListView<Data> FriendsList;

    @FXML
    private ListView<Data> GroupList;

    @FXML
    private ImageView FrendsFind_TB;

    @FXML
    private ImageView GroupFind_TB;


    @FXML
    private Hyperlink Logout;


    @FXML
    private Line Message_un;

    @FXML
    private Line Friends_un;

    @FXML
    private Line Group_un;

    @FXML
    private ImageView Refresh;

    @FXML
    private Circle HeadBorder;

    @FXML
    private ImageView Head;

    @FXML
    private Label NetName;

    @FXML
    private Label Signature;

    @FXML
    private Button Message;

    @FXML
    private Button Friends;

    @FXML
    private Button Group;

    public static ObservableList<Data> Grouplist = FXCollections.observableArrayList();
    public static ObservableList<Data> Friendslist = FXCollections.observableArrayList();

    public static int  id_main=0;                                  //已经登录成功的Chat用户的 Id and 标记

    public static int friend_id_Find=0;                            //添加好友

    public static int group_id_Find=0;                            //添加群聊

    public static int friend_Edit=0;                               //双击的好友

    public static int group_Edit=0;

    public static Socket client;

    public static  SendMessageToServer send;

    @FXML
    void Logout_Action(ActionEvent event) {
        try {
            Stage stage;
            stage = (Stage)Logout.getScene().getWindow();
            stage.close();
            new windows_screen(). NewWindows(new Stage(),"../FXML/login.fxml","登录",600,400);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void FriendsMouseEntered(MouseEvent event) {
        Friends.setOpacity(1);
    }

    @FXML
    void FriendsMouseExited(MouseEvent event) {
        Friends.setOpacity(0.75);
    }

    @FXML
    void Friends_Action(ActionEvent event) {
        Friends_un.setOpacity(1);
        Group_un.setOpacity(0.2);
        Message_un.setOpacity(0.2);
        MessageList.setVisible(false);
        FriendsList.setVisible(true);
        GroupList.setVisible(false);
    }

    @FXML
    void Group_Action(ActionEvent event) {
        Friends_un.setOpacity(0.2);
        Group_un.setOpacity(1);
        Message_un.setOpacity(0.2);
        MessageList.setVisible(false);
        FriendsList.setVisible(false);
        GroupList.setVisible(true);
    }

    @FXML
    void GroupMouseEntered(MouseEvent event) {
        Group.setOpacity(1);
    }

    @FXML
    void GroupMouseExited(MouseEvent event) {
        Group.setOpacity(0.75);
    }

    @FXML
    void MessageMouseEntered(MouseEvent event) {
        Message.setOpacity(1);
    }

    @FXML
    void MessageMouseExited(MouseEvent event) {
        Message.setOpacity(0.75);
    }

    @FXML
    void Message_Action(ActionEvent event) {
        Friends_un.setOpacity(0.2);
        Group_un.setOpacity(0.2);
        Message_un.setOpacity(1);
        MessageList.setVisible(true);
        FriendsList.setVisible(false);
        GroupList.setVisible(false);
    }


    @FXML
    void PersonalData(MouseEvent event) throws Exception{
        new windows_screen(). NewWindows(new Stage(),"../FXML/PersonalData.fxml","个人资料",412, 690);
        //new PersonalData_screen().start(new Stage());
    }


    /**刷新*/
    @FXML
    void Refresh(MouseEvent event) {
        try {
            User user = AddDeleteCheckChange_friend.Select(id_main);
            NetName.setText(user.getNetName());
            Signature.setText(user.getSignature());
            Image image =new Image(user.getHeadPhoto());
            Head.setImage(image);

            //刷新好友列表
            User UserMessage;
            Friendslist.clear();
            String sql_Friendslist = "select * from friendlist where Main="+id_main;
            ResultSet rs_Friendslist=null;
            rs_Friendslist= AddDeleteCheckChange_list.Selectlist(sql_Friendslist);
            while(rs_Friendslist.next()) {
                Data data = new Data();
                data.setNote(rs_Friendslist.getString("Note"));
                data.setId(rs_Friendslist.getInt("Friend"));
                UserMessage = AddDeleteCheckChange_friend.Select(rs_Friendslist.getInt("Friend"));
                data.setHead(UserMessage.getHeadPhoto());
                Friendslist.add(data);
            }
            AddDeleteCheckChange_list.MysqlClose();

            //刷新群聊列表
            Grouplist.clear();
            String sql_Grouplist = "select * from grouplist where Main_id="+id_main;
            ResultSet rs_Grouplist=null;
            rs_Grouplist= AddDeleteCheckChange_list.Selectlist(sql_Grouplist);
            while(rs_Grouplist.next()) {
                Data data = new Data();
                data.setId(rs_Grouplist.getInt("Group_id"));
                String sql = "select * from groupmanagement where Group_id="+rs_Grouplist.getInt("Group_id");
                Group group = AddDeleteCheckChange_Group.Select_Group(sql);
                data.setHead(group.getGroup_head());
                data.setNote(group.getGroup_name());
                Grouplist.add(data);
            }
            AddDeleteCheckChange_list.MysqlClose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void FrendsFind_TB_Clicked(MouseEvent mouseEvent)throws Exception{
        if(FriendFind.getText().equals(""))
            return;
        friend_id_Find=Integer.parseInt(FriendFind.getText());

            User user = AddDeleteCheckChange_friend.Select(friend_id_Find);
        if(user.getId()==0){
            /**没有查找到*/
            new windows_screen(). NewWindows(new Stage(),"../FXML/FindError.fxml","查找失败",392,210);
        }else{
            /**找到了*/
            new windows_screen(). NewWindows(new Stage(),"../FXML/FriendData_Add.fxml","用户个人资料",421,690);
        }
        FriendFind.setText("");
    }

    @FXML
    public void GroupFind_TB_Clicked(MouseEvent mouseEvent) throws Exception{
        if(GroupFind.getText().equals(""))
            return;
        group_id_Find=Integer.parseInt(GroupFind.getText());
        String sql =sql = "select * from groupmanagement where Group_id="+group_id_Find;
        if(!AddDeleteCheckChange_Group.Select_bool(sql)){
            new windows_screen(). NewWindows(new Stage(),"../FXML/FindError.fxml","查找失败",392,210);
            return;
        }else{
            new windows_screen(). NewWindows(new Stage(),"../FXML/GroupData_Add.fxml","Chat群资料",421,702);
        }
        GroupFind.setText("");
    }

    @FXML
    void EnteredHead(MouseEvent event) {
        HeadBorder.setVisible(true);
    }

    @FXML
    void ExitedHead(MouseEvent event) {
        HeadBorder.setVisible(false);
    }

    @FXML
    /**创建群聊*/
    public void CreateGroup_Action(ActionEvent actionEvent) throws Exception{
        new windows_screen(). NewWindows(new Stage(),"../FXML/CreateGroup.fxml","创建群聊",432,312);
    }

    /**初始化*/
    public void initialize(URL url, ResourceBundle rb) {

        ChatClient.Client_main=id_main;
        try {
            send = new ChatClient().RunClient(id_main);               //连接客户端，获得输入输出对象
        } catch (Exception e) {
            e.printStackTrace();
        }

        MessageList.setEditable(true);                                        //设置 可以双击 或 回车 操作，不然 startEdit() 无作用
        MessageList.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {

            int index = 0;
            Data temp = null;

            @Override
            public ListCell<Data> call(ListView<Data> param) {

                param.setOnEditStart(new EventHandler<ListView.EditEvent<Data>>(){

                    @Override
                    public void handle(ListView.EditEvent<Data> event){
                        index = event.getIndex();

                        temp = param.getItems().get(index);
                    }

                });

                ListCell<Data> listcell  = new ListCell<Data>(){


                    @Override
                    public void startEdit(){
                        super.startEdit();
                        try {
                            friend_Edit=temp.getId();
                            new windows_screen(). NewWindows(new Stage(),"../FXML/FriendData.fxml","好友个人资料",421,710);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    //只定义编辑单元格一定要重写的方法
                    protected void updateItem(Data item, boolean empty) {

                        super.updateItem(item, empty);

                        if(!empty && item != null){
                            HBox hbox = new HBox(10);


                            Image img = new Image(item.getHead());
                            ImageView Head = new ImageView();
                            Head.setImage(img);
                            Circle circle1 = new Circle();
                            circle1.setRadius(25);
                            circle1.setCenterX(25);
                            circle1.setCenterY(25);
                            Head.setClip(circle1);
                            Head.setPreserveRatio(true);
                            Head.setFitHeight(50);

                            Label Id =new Label(item.getId()+"");

                            hbox.setAlignment(Pos.CENTER_LEFT);
                            hbox.getChildren().addAll(Head,Id);
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

        FriendsList.setEditable(true);                                        //设置 可以双击 或 回车 操作，不然 startEdit() 无作用
        FriendsList.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {

            int index = 0;
            Data temp = null;

            @Override
            public ListCell<Data> call(ListView<Data> param) {

                param.setOnEditStart(new EventHandler<ListView.EditEvent<Data>>(){

                    @Override
                    public void handle(ListView.EditEvent<Data> event){
                        index = event.getIndex();

                        temp = param.getItems().get(index);
                    }

                });

                ListCell<Data> listcell  = new ListCell<Data>(){


                    @Override
                    public void startEdit(){
                        super.startEdit();
                        try {
                            friend_Edit=temp.getId();
                            new windows_screen(). NewWindows(new Stage(),"../FXML/FriendData.fxml","好友个人资料",421,710);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    //只定义编辑单元格一定要重写的方法
                    protected void updateItem(Data item, boolean empty) {

                        super.updateItem(item, empty);

                        if(!empty && item != null){
                            HBox hbox = new HBox(10);


                            Image img = new Image(item.getHead());
                            ImageView Head = new ImageView();
                            Head.setImage(img);
                            Circle circle1 = new Circle();
                            circle1.setRadius(25);
                            circle1.setCenterX(25);
                            circle1.setCenterY(25);
                            Head.setClip(circle1);
                            Head.setPreserveRatio(true);
                            Head.setFitHeight(50);

                            Label Note =new Label(item.getNote());

                            hbox.setAlignment(Pos.CENTER_LEFT);
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



        GroupList.setEditable(true);                            //设置 可以双击 或 回车 操作，不然 startEdit() 无作用
        GroupList.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {

            int index = 0;
            Data temp = null;

            @Override
            public ListCell<Data> call(ListView<Data> param) {

                param.setOnEditStart(new EventHandler<ListView.EditEvent<Data>>(){

                    @Override
                    public void handle(ListView.EditEvent<Data> event){
                        index = event.getIndex();

                        temp = param.getItems().get(index);
                    }

                });


                ListCell<Data> listcell  = new ListCell<Data>(){

                    @Override
                    public void startEdit(){
                        super.startEdit();
                        try {
                            group_Edit=temp.getId();
                            new windows_screen(). NewWindows(new Stage(),"../FXML/GroupData.fxml","Chat群资料",421,702);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

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
                            Head.setFitHeight(50);
                            Circle circle1 = new Circle();
                            circle1.setRadius(25);
                            circle1.setCenterX(25);
                            circle1.setCenterY(25);
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
            User user = AddDeleteCheckChange_friend.Select(id_main);
            NetName.setText(user.getNetName());
            Signature.setText(user.getSignature());
            HeadBorder.setVisible(false);
            Image image = new Image(user.getHeadPhoto());
            Head.setImage(image);//显示头像
            Circle circle1 = new Circle();
            circle1.setRadius(36);
            circle1.setCenterX(36);
            circle1.setCenterY(36);
            Head.setClip(circle1);

            MessageList.setItems(Friendslist);

            FriendsList.setItems(Friendslist);
            Friendslist.clear();
            String sql_Friendslist = "select * from friendlist where Main="+id_main;
            ResultSet rs_Friendslist=null;
            rs_Friendslist= AddDeleteCheckChange_list.Selectlist(sql_Friendslist);
            while(rs_Friendslist.next()) {
                    Data data = new Data();
                    data.setNote(rs_Friendslist.getString("Note"));
                    data.setId(rs_Friendslist.getInt("Friend"));
                    user = AddDeleteCheckChange_friend.Select(rs_Friendslist.getInt("Friend"));
                    data.setHead(user.getHeadPhoto());
                    Friendslist.add(data);
             }
            AddDeleteCheckChange_list.MysqlClose();



            GroupList.setItems(Grouplist);
            Grouplist.clear();
            String sql_Grouplist = "select * from grouplist where Main_id="+id_main;
            ResultSet rs_Grouplist=null;
            rs_Grouplist= AddDeleteCheckChange_list.Selectlist(sql_Grouplist);
            while(rs_Grouplist.next()) {
                Data data = new Data();
                data.setId(rs_Grouplist.getInt("Group_id"));
                String sql = "select * from groupmanagement where Group_id="+rs_Grouplist.getInt("Group_id");
                Group group = AddDeleteCheckChange_Group.Select_Group(sql);
                data.setHead(group.getGroup_head());
                data.setNote(group.getGroup_name());
                Grouplist.add(data);
            }
            AddDeleteCheckChange_list.MysqlClose();

        } catch (Exception e) {
            e.printStackTrace();
        }
        MessageList.setVisible(true);
        FriendsList.setVisible(false);
        GroupList.setVisible(false);
    }

}
