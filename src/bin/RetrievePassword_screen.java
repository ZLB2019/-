package bin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RetrievePassword_screen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("../view/FXML/RetrievePassword.fxml"));
        primaryStage.setResizable(false);                                                       //固定窗口
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.png"));        //更改窗口图标
        primaryStage.setTitle("找回密码");
        primaryStage.setScene(new Scene(root, 606,467));
        primaryStage.show();                                                                      //显示窗口
    }
}