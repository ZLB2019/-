package view.CLASS;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Calendar;

/**
登录界面
 *
 */

public class login_screen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("../FXML/login.fxml"));
        primaryStage.setResizable(false);                                                       //固定窗口
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.png"));        //更改窗口图标
        primaryStage.setTitle("Chat登录");
        primaryStage.setScene(new Scene(root, 600, 384));
        primaryStage.show();                                                                      //显示窗口

    }
    public static void main(String[] args) {
        launch(args);
    }
}
