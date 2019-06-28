package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AddDeleteCheckChange_friend;
import model.User;
import view.CLASS.windows_screen;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ChangePersonalData_con implements Initializable {

    @FXML
    private TextArea Signature;

    @FXML
    private TextField NetName;

    @FXML
    private TextField Born;

    @FXML
    private MenuButton Sex;

    @FXML
    private Button Save;

    @FXML
    private Button End;


    /**超长 大佬写的正则表达式*/
    private String BornRegular = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[13579][26])00))-02-29)";           /**QQ邮箱的正则表达式*/
    @FXML
    void ChangeSexGirl(ActionEvent event) {
        Sex.setText("女");
    }

    @FXML
    void ChangeSexMan(ActionEvent event) {
        Sex.setText("男");
    }

    @FXML
    void End_onAction(ActionEvent event) {
        Stage stage;
        stage=(Stage)End.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Save_Action(ActionEvent event) throws Exception {
        /**如果用户已经注销,则显示 ‘操作失败’ 窗口 ，阻止正常的弹出窗口*/
        if(Main_con.id_main==0){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/CancellationOfPrompt.fxml","操作失败",392,210);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**输入有误*/
        if(NetName.getText().equals("")|| !Pattern.compile(BornRegular).matcher(Born.getText()).matches()||Signature.getText().length()>42) {
            new windows_screen(). NewWindows(new Stage(),"../FXML/InputError.fxml","输入有误",392,210);
            return;
        }
        /**输入正确*/
        String sql = "update userinformation set NetName='"+NetName.getText()+"',Sex='"+Sex.getText()+"',Born='"+Born.getText()+"',Signature='"+Signature.getText()+"' where Id="+Main_con.id_main;
        AddDeleteCheckChange_friend.Update(sql);
        Stage stage;
        stage=(Stage)End.getScene().getWindow();
        stage.close();
        new windows_screen(). NewWindows(new Stage(),"../FXML/ChangeSuccessful.fxml","修改成功",348, 196);
    }

    /**初始化*/
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Calendar now= Calendar.getInstance();                       //获取时间
            User user = AddDeleteCheckChange_friend.Select(Main_con.id_main);


            NetName.setText(user.getNetName());                         //显示网名

            Sex.setText(user.getSex());

            Born.setText(user.getBorn());

            Signature.setText(user.getSignature());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
