package controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.stream.events.StartDocument;


public class window_con {
    @FXML
    private Button registered;                      /**登录界面的‘注册’按钮*/
    @FXML
    private Button registered_YesOrNo;            /**注册界面的‘注册’按钮*/
    @FXML
    private Button returnload;                  /**注册成功界面的‘确定’按钮*/

    /**登录界面单击‘注册’：
     * 打开注册界面，并且关闭登录界面*/
    public void registered() {
        try {
            new registered_screen().start(new Stage());
            Stage stage;
            stage = (Stage) registered.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**注册界面单击‘注册’：
     * 打开注册成功界面，并且关闭注册界面*/
    public void registered_YesOrNo() {
        try {
            new registered_Yes_screen().start(new Stage());
            Stage stage;
            stage = (Stage) registered_YesOrNo.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**注册成功界面单击‘确定’：
     * 打开登录界面，并且关闭注册成功界面*/
    public void returnload() {
        try {
            new Main().start(new Stage());
            Stage stage;
            stage = (Stage) returnload.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}