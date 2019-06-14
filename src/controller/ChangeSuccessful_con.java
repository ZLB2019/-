package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChangeSuccessful_con {

    @FXML
    private Button ChangeSuccessful;

    @FXML
    void ChangeSuccessful_Close(ActionEvent event) {
        Stage stage;
        stage =(Stage)ChangeSuccessful.getScene().getWindow();
        stage.close();
    }

}
