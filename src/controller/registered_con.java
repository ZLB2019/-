package controller;

import model.*;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.CLASS.windows_screen;

import java.util.regex.Pattern;


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

    public static  String Mail;

    private String Regular = "[1-9][0-9]{5,9}@qq.com";           /**QQ邮箱的正则表达式*/

      String sql;

    /**注册界面单击‘获取验证码’按钮：
     * 发送验证码*/
    @FXML
    public void GetMail() throws Exception {
        /**特殊判断：当              密码位数不是6-16                 或             当密码为空         或             确认密码为空             或                      邮箱格式错误                                   或                两次密码不一致
         * 则调用注册失败窗口
         */
        if(Password.getText().length()<6||Password.getText().length()>16||Password.getText().equals("") || ConfirmPassword.getText().equals("") || !(Pattern.compile(Regular).matcher(mailtext.getText()).matches())||!(Password.getText().equals(ConfirmPassword.getText()))){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/registered_No.fxml","输入有误",392,210);
                //new registered_No_screen().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Verificationode.setText("");                //将 '验证码' 文本清空，  因为验证码已经失效
            return;                                      //  提前结束此方法，因为已经判定输入错误
        }
        String UserMail;
        UserMail = mailtext.getText();
        new mail().SendMail(UserMail);
    }


    /**注册界面单击‘注册’：
     * 打开注册成功界面，并且关闭注册界面*/
    @FXML
    public void registered_YesOrNo() {
        String Verificationode_re=null;
        Verificationode_re = Verificationode.getText();                     //获取验证码文本框中的字符串


        /**特殊判断：  当              密码位数不是6-16                 或           密码为空         或             确认密码为空             或          邮箱格式错误
         * 则调用注册失败窗口
         * ps ：为了减少下面 if（）语句里的内容
         */
        if(Password.getText().length()<6||Password.getText().length()>16||Password.getText().equals("") || ConfirmPassword.getText().equals("") || !(Pattern.compile(Regular).matcher(mailtext.getText()).matches())){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/registered_No.fxml","输入有误",392,210);
                //new registered_No_screen().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Verificationode.setText("");                //将 '验证码' 文本清空，  因为验证码已经失效
            return;                                      //  提前结束此方法，因为已经判定输入错误

        }

        /**当           @验证码不正确    或者    @验证码为空   或者   @两次密码不一样      的时候 */
        if(!(Verificationode_re.equals(mail.RetrunVerificationCode))||Verificationode_re.equals("")||!(Password.getText().equals(ConfirmPassword.getText()))) {
            /**写显示注册失败窗口*/
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/registered_No.fxml","输入有误",392,210);
                //new registered_No_screen().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mail.RetrunVerificationCode="";             //重置验证码
            Verificationode.setText("");                 //将 '验证码' 文本清空，  因为验证码已经失效
        }
        else
        {
            /**否则：
             * 显示注册成功窗口
             * */
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/registered_Yes.fxml","注册成功",405,259);
                //new registered_Yes_screen().start(new Stage());
                Stage stage;
                stage = (Stage) registered_YesOrNo.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            passwrod = Password.getText();                      //获取密码
            mail.RetrunVerificationCode="";                 //重置验证码
            Mail=mailtext.getText();
//            System.out.println(registered_Yes_con.NameInt);
//            System.out.println(passwrod);
//            System.out.println(Mail);
            try {
                sql ="insert into userinformation(id,password,Mail)"
                        +" values("+registered_Yes_con.NameInt+",'"+passwrod+"','"+Mail+"')";
                AddDeleteCheckChange.Insert(sql);                     /**注册成功，将账号、密码存入数据库*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}