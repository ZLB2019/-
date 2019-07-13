package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.CLASS.*;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.Main_con.id_main;

public class login_con implements Initializable {
    @FXML
    private Hyperlink RetrievePassword;                 /**登录界面的‘忘记密码’超链接*/
    @FXML
    public  TextField UserName;                        /**登录界面的‘账号’输入框*/
    @FXML
    private PasswordField Password;                  /**登录界面的‘密码’输入框*/
    @FXML
    private Hyperlink registered;                       /**登录界面的‘注册’按钮*/
    @FXML
    private Button Load;                              /**登录界面的‘登录’按钮*/
    @FXML
    private Button load_no_return_load;                  /**登录失败界面的‘返回’按钮*/

    /**登录界面单击‘注册’：
     * 打开注册界面，并且关闭登录界面*/
    @FXML
    public void registered() throws Exception{
            new windows_screen(). NewWindows(new Stage(),"../FXML/registered.fxml","注册Chat",555,624);
            //new registered_screen().start(new Stage());
            Stage stage;
            stage = (Stage) registered.getScene().getWindow();
            stage.close();
    }

    /**登录界面单击‘登录’：
     * 账号密码错误： 关闭登录界面，弹出登录失败对话框
     * 正确则关闭登录界面，进去Chat主界面
     * */
    @FXML
    public void Load() throws Exception{
        /**特殊判断
         * 当账号或者密码框为 null 时
         * 调用登录失败窗口
         * 并退出此方法
         * */
        if(UserName.getText().equals("")||Password.getText().equals("")) {
            new windows_screen(). NewWindows(new Stage(),"../FXML/login_No.fxml","登录失败",445,235);
            //new login_No_screen().start(new Stage());
            return;                                                  //  提前结束此方法，因为已经判定输入错误
        }

        /**获取账号*/
        int id = 0;
        try {
            id = Integer.parseInt(UserName.getText());               //获得 int 型的账号
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        /**获取密码*/
        String pass = Password.getText();

        //用于获取用户信息
        User UserMessage= AddDeleteCheckChange_friend.Select(id);
        /**判断输入的账号密码是否匹配
         * 匹配则进入’主界面‘，关闭’登录‘界面
         * 不匹配贼弹出’登录失败‘界面
         */
        if(!(UserMessage.getId()==0||!(UserMessage.getPassword().equals(pass)))){
            Main_con.id_main = id;              //该id用户已经登录
            new windows_screen(). NewWindows(new Stage(),"../FXML/Main.fxml","Chat",412,707);
            //new Main_screen().start(new Stage());
            Stage stage;
            stage = (Stage) registered.getScene().getWindow();
            stage.close();
        }
        else {
            new windows_screen(). NewWindows(new Stage(),"../FXML/login_No.fxml","登录失败",445,235);
            //new login_No_screen().start(new Stage());
        }
    }

    @FXML
    void Load_Entered(MouseEvent event) {
        Load.setStyle(" -fx-background-color: #00FFFF; -fx-background-radius: 80;");
    }

    @FXML
    void Load_Exited(MouseEvent event) {
        Load.setStyle("-fx-background-color : #00F5FF ; -fx-background-radius : 80;");
    }

    /**单击‘忘记密码’弹出 RetrievePassword 窗口*/
    @FXML
    void OpenRetrievePassword(ActionEvent event) throws Exception{
        new windows_screen(). NewWindows(new Stage(),"../FXML/RetrievePassword.fxml","找回密码",606,467);
       //new RetrievePassword_screen().start(new Stage());
    }
    public void initialize(URL url, ResourceBundle rb)
    {
        if(!registered_Yes_con.name.equals("")){
            UserName.setText(registered_Yes_con.name);
            registered_Yes_con.name="";                         //标记无 忘记密码 界面跳转 登录界面
        }
        if(id_main!=0){
            UserName.setText(""+ id_main);
            id_main=0;                                 //标记无 用户 已经下线
        }
    }

}