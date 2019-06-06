package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Main_con {
    @FXML
    private Label Message_un;

    @FXML
    private Label Contact_un;

    @FXML
    private Button Message;

    @FXML
    private Button Contact;

    @FXML
    void ContactDelUnderline(MouseEvent event) {                                //鼠标离开‘联系人’按钮
        Contact.setUnderline(false);                                            //取消下划线
    }

    @FXML
    void ContactShowUnderline(MouseEvent event) {                               //鼠标进入‘联系人’按钮
        Contact.setUnderline(true);                                             //显示下划线
    }

    @FXML
    void Contact_Action(ActionEvent event) {                                    //单击‘联系人’按钮后
        Message_un.setUnderline(false);                                         //‘消息’标签不显示下划线
        Contact_un.setUnderline(true);                                          //‘联系人’标签显示下划线
    }

    @FXML
    void MessageDelUnderline(MouseEvent event) {                                //鼠标离开‘消息’按钮
        Message.setUnderline(false);                                            //取消下划线
    }

    @FXML
    void MessageShowUnderline(MouseEvent event) {                               //鼠标进入‘消息’按钮
        Message.setUnderline(true);                                             //显示下划线
    }

    @FXML
    void Message_Action(ActionEvent event) {                                    //单击‘消息’按钮后
        Message_un.setUnderline(true);                                         //‘消息’标签显示下划线
        Contact_un.setUnderline(false);                                          //‘联系人’标签不显示下划线
    }

}
