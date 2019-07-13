package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AddDeleteCheckChange_friend;
import view.CLASS.windows_screen;

public class ChangePassword_Ret_con {
    @FXML
    private Button Determine_Re;                           //修改密码界面‘确认’按钮

    @FXML
    private PasswordField Password;                        //修改密码界面 密码文本框

    @FXML
    private PasswordField ConfirmPassword;                  //修改密码界面  确认密码文本框

    @FXML
    void Determine_Re_Entered(MouseEvent event) {
        Determine_Re.setStyle("-fx-background-color :  #FF4040 ;");
    }

    @FXML
    void Determine_Re_Exited(MouseEvent event) {
        Determine_Re.setStyle("-fx-background-color :  #FF0000 ;");
    }

    /**修改密码*/
    @FXML
    public void Determine_Re_Action(ActionEvent event) throws Exception {
        if(Password.getText().length()>=6&&Password.getText().length()<=16 && Password.getText().equals(ConfirmPassword.getText())){
            String sql = "update userinformation set Password='"+Password.getText()+"' where Id="+RetrievePassword_con.id_Ret;
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
