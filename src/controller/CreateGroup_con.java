package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AddDeleteCheckChange_Group;
import view.CLASS.windows_screen;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static controller.Main_con.*;

public class CreateGroup_con implements Initializable {

    @FXML
    private Label Group_id;

    @FXML
    private TextField Group_name;

    @FXML
    private Button CreateGroup;

    public static int Group_id_int = 0;
    public static String Group_id_string = "";


    @FXML
    void CreateGroup_Action(ActionEvent event) {
        String group_name = Group_name.getText();
        String sql ="insert into groupmanagement(Group_main,Group_id,Group_name,Group_head)"
                +" values("+id_main+","+Group_id_int+",'"+group_name+"','file:///E:/聊天器图片素材/初始头像.png')";
        try {


            AddDeleteCheckChange_Group.Insert(sql);
            sql ="insert into grouplist(Main_id,Group_id)"
                    +" values("+id_main+","+Group_id_int+")";
            AddDeleteCheckChange_Group.Insert(sql);
            new windows_screen(). NewWindows(new Stage(),"../FXML/CreateGroupSuccessful.fxml","创建群聊成功",392,210);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage;
        stage = (Stage)CreateGroup.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL url, ResourceBundle rb) {
        boolean flag = true;

        while(flag){                                  /**判重*/
            Random random =new Random();
            Group_id_int = random.nextInt(9000000)+1000000;
            String sql =sql = "select * from groupmanagement where Group_id="+Group_id_int;
            try {
                flag=AddDeleteCheckChange_Group.Select_bool(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Group_id_string = Group_id_int + "";
        Group_id.setText(Group_id_string);                                  /**初始化 群号码 标签*/
    }
}
