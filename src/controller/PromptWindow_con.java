package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PromptWindow_con {

    @FXML
    private Button registered_No_Close;                       /**提示界面的‘确定’按钮*/

    @FXML
    public void registered_No_Close(ActionEvent event) {
        try {
            Stage stage;
            stage = (Stage) registered_No_Close.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
