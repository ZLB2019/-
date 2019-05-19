package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class registered_Yes_screen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("../view/registered_Yes.fxml"));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.jpg"));
        primaryStage.setTitle("注册成功");
        primaryStage.setScene(new Scene(root, 405, 259));
        primaryStage.show();
    }
}