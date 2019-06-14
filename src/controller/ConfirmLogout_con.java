package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmLogout_con {

    @FXML
    private Button Determine;

    @FXML
    private Button Cancel;

    @FXML
    void Cancel_Action(ActionEvent event) {
        Stage stage;
        stage= (Stage)Determine.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Determine_Action(ActionEvent event) {

    }

}
