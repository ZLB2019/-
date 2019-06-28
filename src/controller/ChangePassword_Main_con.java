package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.AddDeleteCheckChange_friend;
import view.CLASS.windows_screen;

public class ChangePassword_Main_con {
    @FXML
    private Button Determine_Re;                           //修改密码界面‘确认’按钮

    @FXML
    private PasswordField Password;                        //修改密码界面 密码文本框

    @FXML
    private PasswordField ConfirmPassword;                  //修改密码界面  确认密码文本框

    /**修改密码*/
    @FXML
    public void Determine_Re_Action(ActionEvent event) throws Exception {
        /**如果用户已经注销,则显示 ‘操作失败’ 窗口 ，阻止正常的弹出窗口*/
        if(Main_con.id_main==0){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/CancellationOfPrompt.fxml","操作失败",392,210);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(Password.getText().length()>=6&&Password.getText().length()<=16 && Password.getText().equals(ConfirmPassword.getText())){
            String sql = "update userinformation set Password='"+Password.getText()+"' where Id="+Main_con.id_main;
            AddDeleteCheckChange_friend.Update(sql);
            Stage stage;
            stage = (Stage)Determine_Re.getScene().getWindow();
            stage.close();
            new windows_screen(). NewWindows(new Stage(),"../FXML/ChangeSuccessful.fxml","修改成功",348, 196);
            //new RetrievePassword_screen().start(new Stage());
        }
        else
            new windows_screen(). NewWindows(new Stage(),"../FXML/InputError.fxml","输入有误",392,210);
    }
}
