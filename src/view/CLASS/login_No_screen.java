package view.CLASS;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class login_No_screen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("../FXML/login_No.fxml"));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.png"));
        primaryStage.setTitle("登录失败");
        primaryStage.setScene(new Scene(root, 445, 235));
        primaryStage.show();
    }
}
