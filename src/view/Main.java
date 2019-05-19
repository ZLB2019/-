package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
登录界面
 *
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("load.fxml"));
        primaryStage.setResizable(false);                                                       //固定窗口
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.jpg"));        //更改窗口图标
        primaryStage.setTitle("QQ登录");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();                                                                      //显示窗口
    }
    public static void main(String[] args) {
        launch(args);
    }
}
