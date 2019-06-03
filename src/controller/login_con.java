package controller;

import com.mysql.cj.protocol.x.StatementExecuteOk;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.internal.dynalink.beans.StaticClass;
import view.CLASS.*;
import model.*;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Source;
import java.net.URL;
import java.util.ResourceBundle;

public class login_con implements Initializable {
    @FXML
    public  TextField UserName;                        /**登录界面的‘账号’输入框*/
    @FXML
    private PasswordField Password;                  /**登录界面的‘密码’输入框*/
    @FXML
    private Button registered;                       /**登录界面的‘注册’按钮*/
    @FXML
    private Button Load;                              /**登录界面的‘登录’按钮*/
    @FXML
    private Button load_no_return_load;                  /**登录失败界面的‘返回’按钮*/

    /**登录界面单击‘注册’：
     * 打开注册界面，并且关闭登录界面*/
    public void registered() throws Exception{
            new registered_screen().start(new Stage());
            Stage stage;
            stage = (Stage) registered.getScene().getWindow();
            stage.close();
    }

    /**登录界面单击‘登录’：
     * 账号密码错误： 关闭登录界面，弹出登录失败对话框
     * 正确则关闭登录界面，进去Chat主界面
     * */
    public void Load() throws Exception{
        int id = 0;

        /**特殊判断
         * 当账号或者密码框为 null 时
         * 调用登录失败窗口
         * 并退出此方法
         * */
        if(UserName.getText().equals("")||Password.getText().equals("")) {
            new login_No_screen().start(new Stage());
            return;
        }

        /**获取账号*/
        try {
            id = Integer.parseInt(UserName.getText());               //获得 int 型的账号
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        /**获取密码*/
        String pass = Password.getText();

        if(judge.judge(id,pass)){                                    //判断输入的账号密码是否匹配
            new Main_screen().start(new Stage());
            Stage stage;
            stage = (Stage) registered.getScene().getWindow();
            stage.close();
        }
        else {
            new login_No_screen().start(new Stage());
            Stage stage;
            stage = (Stage) Load.getScene().getWindow();
            stage.close();
        }
    }


    /**登录失败比界面单击‘确定’：
     * 关闭登录失败界面
     * 打开登录界面
     * */
    public void load_no_return_load() {
        try {
            new login_screen().start(new Stage());
            Stage stage;
            stage = (Stage) load_no_return_load.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        UserName.setText("456");
    }
}