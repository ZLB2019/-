package view.CLASS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class windows_screen extends Application {

    Stage stage =new Stage();
    public void NewWindows(Stage primaryStage,String fxml,String Title,int w,int h) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource(fxml));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.png"));
        primaryStage.setTitle(Title);
        primaryStage.setScene(new Scene(root, w, h));
        primaryStage.show();
    }
    @Override
    public void start(Stage primaryStage){
    }
}

/**login            :   new windows_screen(). NewWindows(new Stage(),"../FXML/login.fxml","登录",600,400);
 * registered       :   new windows_screen(). NewWindows(new Stage(),"../FXML/registered.fxml","注册Chat",555,624);
 * login_No         :   new windows_screen(). NewWindows(new Stage(),"../FXML/login_No.fxml","登录失败",445,235);
 *Main              :   new windows_screen(). NewWindows(new Stage(),"../FXML/Main.fxml","Chat",412,707);
 *registered_No     :   new windows_screen(). NewWindows(new Stage(),"../FXML/registered_No.fxml","输入有误",392,210);
 *registered_Yes    :   new windows_screen(). NewWindows(new Stage(),"../FXML/registered_Yes.fxml","注册成功",405,259);
 *RetrievePassword  :   new windows_screen(). NewWindows(new Stage(),"../FXML/RetrievePassword.fxml","找回密码",606,467);
 *ChangePassword    :    new windows_screen(). NewWindows(new Stage(),"../FXML/ChangePassword.fxml","修改密码",633,389);
 */
