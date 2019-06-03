package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class registered_No_con {

    @FXML
    private Button registered_No_Close;                       /**注册失败界面的‘确定’按钮*/

    public void registered_No_Close() {
        try {
            Stage stage;
            stage = (Stage) registered_No_Close.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
