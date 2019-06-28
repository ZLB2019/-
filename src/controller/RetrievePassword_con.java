package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AddDeleteCheckChange_friend;
import model.User;
import model.mail;
import view.CLASS.windows_screen;

import java.util.regex.Pattern;

public class RetrievePassword_con {

    @FXML
    private TextField MailText;                             //找回密码界面 邮箱文本框

    @FXML
    private TextField Verificationode;                      //找回密码界面 验证码文本框

    @FXML
    private Button GetMail;                                 //找回密码界面 获取验证码 按钮

    @FXML
    private Button Determine;                                //找回密码界面 ‘确定’ 按钮

    @FXML
    private TextField UserName;                             //找回密码界面 账号文本框


    public static int id_Ret = 0;                                             //忘记密码界面需要修改的 账号and标记

    private String QQMailRegular = "[1-9][0-9]{5,9}@qq.com";           /**QQ邮箱的正则表达式*/

    /**找回密码界面单击’确定‘按钮*/
    @FXML
    void Determine_Action(ActionEvent event) throws Exception {
        /**账号为空
         * 调用 输入有误窗口
         */
        if(UserName.getText().equals("")){
            new windows_screen(). NewWindows(new Stage(),"../FXML/InputError.fxml","输入有误",392,210);
            return;
        }
        /**获取int 的id*/
        try {
            id_Ret=Integer.parseInt(UserName.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //用于获取用户信息
        User UserMessage= AddDeleteCheckChange_friend.Select(id_Ret);
        /**特殊判断：  当           邮箱格式错误                           或       账号为空               或    账号、邮箱不匹配
         * 则调用修改密码窗口
         */
        if(!(Pattern.compile(QQMailRegular).matcher(MailText.getText()).matches())||UserName.getText().equals("")||!(MailText.getText().equals(UserMessage.getMail()))){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/registered_No.fxml","输入有误",392,210);
                //new registered_No_screen().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Verificationode.setText("");                //将 '验证码' 文本清空，  因为验证码已经失效
            return;                                      //  提前结束此方法，因为已经判定输入错误
        }
        /**显示 修改密码_Ret 窗口,并 找回密码 窗口*/
        new windows_screen(). NewWindows(new Stage(),"../FXML/ChangePassword_Ret.fxml","修改密码",633,389);

        Stage stage;
        stage = (Stage)Determine.getScene().getWindow();
        stage.close();
    }

    /**忘记密码界面单击‘获取验证码’按钮：
     * 发送验证码*/
    @FXML
    public void GetMail_Action() throws Exception {
        /**账号为空
         * 调用 输入有误窗口
         */
        if(UserName.getText().equals("")){
            new windows_screen(). NewWindows(new Stage(),"../FXML/InputError.fxml","输入有误",392,210);
            return;
        }
        /**获取int 的id*/
        try {
            id_Ret=Integer.parseInt(UserName.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //用于获取用户信息
        User UserMessage= AddDeleteCheckChange_friend.Select(id_Ret);
        /**判断：  当           邮箱格式错误                                或    账号、邮箱不匹配
         * 则调用注册失败窗口
         */
        if(!(Pattern.compile(QQMailRegular).matcher(MailText.getText()).matches())||!(MailText.getText().equals(UserMessage.getMail()))){
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
        UserMail = MailText.getText();
        new mail().SendMail(UserMail);
    }

}
