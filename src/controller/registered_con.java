package controller;

import model.*;

import com.sun.javafx.image.BytePixelSetter;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.CLASS.login_screen;
import view.CLASS.registered_No_screen;
import view.CLASS.registered_Yes_screen;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class registered_con {
    @FXML
    private Button registered_YesOrNo;                            /**注册界面的‘注册’按钮*/
    @FXML
    private Button GetMail;                                   /**注册界面的‘获取验证码’按钮*/

    @FXML
    private TextField mailtext;                                 /**注册界面的‘邮件文本框’*/
    @FXML
    private TextField Verificationode;                          /**注册界面的‘验证码’文本框*/

    @FXML
    private PasswordField Password;                         /**注册界面的‘密码’文本框*/
    @FXML
    private PasswordField ConfirmPassword;                  /**注册界面的‘确认密码’文本框*/

    public static String passwrod = "";

    private String Regular = "[\\w&&^[0-9]]{6,}";           /**QQ邮箱的正则表达式*/

    /**注册界面单击‘获取验证码’按钮：
     * 发送验证码*/
    public void GetMail() throws Exception{
            String UserMail;
            UserMail=mailtext.getText();
            new mail_con().SendMail(UserMail);

    }

    /**注册界面单击‘注册’：
     * 打开注册成功界面，并且关闭注册界面*/
    public void registered_YesOrNo() {
        String Verificationode_re=null;
        Verificationode_re = Verificationode.getText();                     //获取验证码文本框中的字符串

        /**特殊判读
         * 当密码和确认密码为空 或
         * 邮箱格式错误
         *
         */

        /**当              @验证码不正确    或者    @验证码为空   或者   @两次密码不一样      的时候 */
        if(!(Verificationode_re.equals(mail_con.RetrunVerificationCode))||Verificationode_re.equals("")||!(Password.getText().equals(ConfirmPassword.getText()))) {
            /**写显示注册失败窗口*/
            try {
                new registered_No_screen().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Verificationode.setText("");
        }
        else
        {
            /**显示注册成功窗口*/
            try {
                new registered_Yes_screen().start(new Stage());
                Stage stage;
                stage = (Stage) registered_YesOrNo.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            passwrod = Password.getText();                      //获取密码
            mail_con.RetrunVerificationCode="";                 //重置验证码
            try {
                AddDeleteCheckChange.Add();                     /**注册成功，将账号、密码存入数据库*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}