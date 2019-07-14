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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import model.*;
import org.omg.CORBA.TypeCodePackage.BadKind;
import view.CLASS.windows_screen;

import javax.swing.*;
import java.awt.event.MouseAdapter;
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
    private AnchorPane Expression;

    @FXML
    private Button SendMessage;

    @FXML
    private ListView<MessageData> Chat;

    @FXML
    private TextArea Message;

    @FXML
    private ImageView QQshow;

    @FXML
    private ImageView Expression_No;

    @FXML
    private ImageView Expression_Yes;

    @FXML
    private ImageView Image_No;

    @FXML
    private ImageView Image_Yes;

    @FXML
    private MenuButton Phrase;

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

    private ImageView bk;

    public ObservableList<MessageData> chat = FXCollections.observableArrayList();

    private int ExpressionVisable=0;

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
    void Expression_No_Entered(MouseEvent event) {
        Expression_Yes.setVisible(true);
    }

    @FXML
    void Expression_Yes_Clicked(MouseEvent event) throws Exception{
        if(ExpressionVisable == 0)
        {

            class ExpressionRunnable implements Runnable   {
                public void run()   {
                    bk.setVisible(true);
                    Expression.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            int x,y;
                            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                System.out.println("双击了条目");
                                x = (int)(event.getX() / 50) + 1;
                                y = (int)(event.getY() / 50);
                                if( x == 6 ) x = 5;
                                if( y == 4 ) y = 3;

                                /**将值改成隐藏*/
                                Expression.setVisible(false);
                                ExpressionVisable = 0;

                                if( y*5+x >= 10 )
                                    Message.setText(Message.getText() + "/BQ0" + (y*5+x));
                                else
                                    Message.setText(Message.getText() + "/BQ00" + (y*5+x));
                            } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                                System.out.println("单击了该窗口的x:" + event.getX() + "Y:" + event.getY());
                                x = (int)(event.getX() / 50) * 50;
                                y = (int)(event.getY() / 50) * 50;
                                if( x == 250 ) x = 200;
                                if( y == 200 ) y = 150;
                                bk.setLayoutX(x);
                                bk.setLayoutY(y);
                            }
                        }
                    });
                }
              }
            Expression.setVisible(true);
            ExpressionRunnable expressionRunnable = new ExpressionRunnable();
            new Thread(expressionRunnable).start();
            ExpressionVisable = 1;
        }
        else
        {
            Expression.setVisible(false);
            ExpressionVisable = 0;
        }
    }

    @FXML
    void Expression_Yes_Exited(MouseEvent event) {
        Expression_Yes.setVisible(false);
    }

    @FXML
    void Image_No_Entered(MouseEvent event) {
        Image_Yes.setVisible(true);
    }

    @FXML
    void Image_Yes_Exited(MouseEvent event) {
        Image_Yes.setVisible(false);
    }

    @FXML
    void Image_Yes_Clicked(MouseEvent event) throws Exception{
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

    @FXML
    void Phrase_Entered(MouseEvent event) {
        Phrase.setStyle("-fx-background-color : #F2F2F2; -fx-focus-traversable: 7; -fx-border-color: #CCCCCC;");
    }

    @FXML
    void Phrase_Exited(MouseEvent event) {
        Phrase.setStyle("-fx-background-color :  #FFFFFF; -fx-focus-traversable: 7; -fx-border-color: #CCCCCC;");
    }

    @FXML
    void SendMessage_Entered(MouseEvent event) {
        SendMessage.setStyle("-fx-background-color : #ABABAB");
    }

    @FXML
    void SendMessage_Exited(MouseEvent event) {
        SendMessage.setStyle("-fx-background-color :  #8F8F8F");
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

        String qz = "file:///E:/聊天器图片素材/小表情/0";
        VBox Dvbox = new VBox(10);
        for(int i = 0 ;i<4;i++)
        {
            HBox hBox = new HBox(10);
            for(int j = 1;j<=5;j++)
            {
                Image image ;
                if(i*5+j>=10)
                    image = new Image(qz + (i*5+j) + ".png");
                else
                    image = new Image(qz + "0" + (i*5+j) + ".png");
                ImageView expression = new ImageView(image);
                expression.setFitWidth(40);
                expression.setFitHeight(40);
                hBox.getChildren().addAll(expression);
            }
            Dvbox.getChildren().addAll(hBox);
        }
        Expression.getChildren().add(Dvbox);

        /**表情选定边框显示*/
        String bkString = "file:///E:/聊天器图片素材/小表情/边框.png";
        Image image = new Image(bkString);
        bk = new ImageView(image);
        Expression.getChildren().add(bk);
        bk.setFitHeight(40);
        bk.setFitWidth(40);
        bk.setVisible(false);

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
                                    String LabelStr = "";
                                    char[] labelstr = item.getMessage().toCharArray();
                                    String ImageStr = "";
                                    VBox vbox = new VBox(10);
                                    HBox JBhbox = new HBox(1);
                                    String qz = "file:///E:/聊天器图片素材/小表情/";

                                    if (item.getMain() == id_main) {                    //己方
                                        Label NetAndTimeAndMessage = new Label(item.getTime() + "  " + user.getNetName());
                                        hbox.setAlignment(Pos.CENTER_RIGHT);
                                        hbox.getChildren().addAll(NetAndTimeAndMessage, Head);
                                        vbox.setAlignment(Pos.CENTER_RIGHT);
                                        vbox.getChildren().add(hbox);
                                        for(int i = 0 ; i < labelstr.length ; i++){
                                            if(labelstr[i] == '/' && labelstr[i+1] == 'B' && labelstr[i+2] == 'Q'){
                                                ImageStr +="" + labelstr[i+3]+labelstr[i+4]+labelstr[i+5];
                                                Label label = new Label(LabelStr);
                                                label.setStyle("-fx-font-size: 20;");
                                                //System.out.println("输入文本："+LabelStr+"    输入一次表情:" + qz + ImageStr + ".png");
                                                Image bqimage = new Image(qz + ImageStr + ".png");
                                                ImageView bqimageview = new ImageView(bqimage);
                                                bqimageview.setFitWidth(30);
                                                bqimageview.setFitHeight(30);
                                                JBhbox.getChildren().addAll(label,bqimageview);
                                                i+=5;
                                                LabelStr = "";
                                                ImageStr = "";
                                            }else if(labelstr[i] == '\n'){
                                                JBhbox.setAlignment(Pos.CENTER_RIGHT);
                                                Label label = new Label(LabelStr);
                                                label.setStyle("-fx-font-size: 20;");
                                                JBhbox.getChildren().addAll(label);
                                                vbox.getChildren().add(JBhbox);
                                                JBhbox = new HBox(1);
                                                LabelStr = "";
                                            }else{
                                                LabelStr += labelstr[i];
                                            }
                                        }
                                        JBhbox.setAlignment(Pos.CENTER_RIGHT);
                                        Label label = new Label(LabelStr);
                                        label.setStyle("-fx-font-size: 20;");
                                        JBhbox.getChildren().addAll(label);
                                        vbox.getChildren().add(JBhbox);
                                    } else {                                            //对方
                                        Label NetAndTimeAndMessage = new Label( user.getNetName()  + "  " +item.getTime());
                                        hbox.setAlignment(Pos.CENTER_LEFT);
                                        hbox.getChildren().addAll(Head,NetAndTimeAndMessage);
                                        vbox.setAlignment(Pos.CENTER_LEFT);
                                        vbox.getChildren().add(hbox);
                                        for(int i = 0 ; i < labelstr.length ; i++){
                                            if(labelstr[i] == '/' && labelstr[i+1] == 'B' && labelstr[i+2] == 'Q'){
                                                ImageStr +="" + labelstr[i+3]+labelstr[i+4]+labelstr[i+5];
                                                Label label = new Label(LabelStr);
                                                label.setStyle("-fx-font-size: 20;");
                                                //System.out.println("输入文本："+LabelStr+"    输入一次表情:" + qz + ImageStr + ".png");
                                                Image bqimage = new Image(qz + ImageStr + ".png");
                                                ImageView bqimageview = new ImageView(bqimage);
                                                bqimageview.setFitWidth(30);
                                                bqimageview.setFitHeight(30);
                                                JBhbox.getChildren().addAll(label,bqimageview);
                                                i+=5;
                                                LabelStr = "";
                                                ImageStr = "";
                                            }else if(labelstr[i] == '\n'){
                                                JBhbox.setAlignment(Pos.CENTER_LEFT);
                                                Label label = new Label(LabelStr);
                                                label.setStyle("-fx-font-size: 20;");
                                                JBhbox.getChildren().addAll(label);
                                                vbox.getChildren().add(JBhbox);
                                                JBhbox = new HBox(1);
                                                LabelStr = "";
                                            }else{
                                                LabelStr += labelstr[i];
                                            }
                                        }
                                        JBhbox.setAlignment(Pos.CENTER_LEFT);
                                        Label label = new Label(LabelStr);
                                        label.setStyle("-fx-font-size: 20;");
                                        JBhbox.getChildren().addAll(label);
                                        vbox.getChildren().add(JBhbox);
                                    }
                                    this.setGraphic(vbox);
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
