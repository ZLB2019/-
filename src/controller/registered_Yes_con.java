package controller;

import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.CLASS.login_screen;
import view.CLASS.registered_Yes_screen;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class registered_Yes_con implements Initializable {
    @FXML
    private Button registered_Yes_return_load;                  /**注册成功界面的‘确定’按钮*/
    @FXML
    private Label UserName;                                 /**注册成功界面的‘UserName’标签*/


    public static String name = "";
    public static int NameInt = 0;

    /**注册成功界面单击‘确定’：
     * 打开登录界面，并且关闭注册成功界面*/
    public void registered_Yes_return_load() {
        try {
            new login_screen().start(new Stage());
            Stage stage;
            stage = (Stage) registered_Yes_return_load.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    /**初始化*/
    public void initialize(URL url, ResourceBundle rb) {
        Random random = new Random();
        NameInt= random.nextInt(900000000)+100000000;
        name = name + NameInt;
        UserName.setText(name);
    }

}