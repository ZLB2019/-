package bin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PersonalData_screen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("../view/FXML/PersonalData.fxml"));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.png"));
        primaryStage.setTitle("个人资料");
        primaryStage.setScene(new Scene(root, 412, 690));
        primaryStage.show();
    }
}