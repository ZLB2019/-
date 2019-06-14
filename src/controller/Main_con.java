package controller;

import bin.PersonalData_screen;
import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.AddDeleteCheckChange;
import model.User;
import view.CLASS.windows_screen;
import model.User;

import java.awt.*;
import java.awt.font.ImageGraphicAttribute;
import java.awt.geom.GeneralPath;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_con implements Initializable {


    @FXML
    private TextField GroupFind;

    @FXML
    private TextField FriendFind;

    @FXML
    private ListView MessageList;

    @FXML
    private ListView FriendsList;

    @FXML
    private ListView GroupList;

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
    private AnchorPane HeadBorder;

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


    private ObservableList<String> Messagelist = FXCollections.observableArrayList();
    private ObservableList<String> Grouplist = FXCollections.observableArrayList();
    private ObservableList<String> Friendslist = FXCollections.observableArrayList();

    public static int  id_main=0;                                  //已经登录成功的Chat用户的 Id and 标记

    public static int id_Find=0;

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
            User user = AddDeleteCheckChange.Select(id_main);
            NetName.setText(user.getNetName());
            Signature.setText(user.getSignature());
            Image image =new Image(user.getHeadPhoto());
            Head.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void FrendsFind_TB_Clicked(MouseEvent mouseEvent)throws Exception{
        if(FriendFind.getText().equals(""))
            return;
        id_Find=Integer.parseInt(FriendFind.getText());

            User user = AddDeleteCheckChange.Select(id_Find);
        if(user.getId()==0){
            /**没有查找到*/
            new windows_screen(). NewWindows(new Stage(),"../FXML/registered_No.fxml","查找失败",392,210);
        }else{
            /**找到了*/
            new windows_screen(). NewWindows(new Stage(),"../FXML/FriendData_Add.fxml","用户个人资料",421,690);
        }

    }

    @FXML
    public void GroupFind_TB_Clicked(MouseEvent mouseEvent) {
    }

    @FXML
    void EnteredHead(MouseEvent event) {
        HeadBorder.setVisible(true);
    }

    @FXML
    void ExitedHead(MouseEvent event) {
        HeadBorder.setVisible(false);
    }

    public void initialize(URL url, ResourceBundle rb) {
        try {
            User user = AddDeleteCheckChange.Select(id_main);
            NetName.setText(user.getNetName());
            Signature.setText(user.getSignature());
            HeadBorder.setVisible(false);
            Image image = new Image(user.getHeadPhoto());
            Head.setImage(image);                                           //显示头像

            MessageList.setItems(Messagelist);
            Messagelist.add("喂，在吗");
            Messagelist.add("你在干嘛");
            Messagelist.add("emmm。。");

            FriendsList.setItems(Friendslist);
            Friendslist.add("张三");
            Friendslist.add("李四");
            Friendslist.add("王五");

            GroupList.setItems(Grouplist);
            Grouplist.add("小阁子");
            Grouplist.add("IT成员");
            Grouplist.add("秃头组织");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
