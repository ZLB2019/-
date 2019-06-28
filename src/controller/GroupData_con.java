package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import model.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static controller.Main_con.group_Edit;
import static controller.Main_con.group_id_Find;

public class GroupData_con implements Initializable {

    @FXML
    private ImageView Group_head;

    @FXML
    private Label Group_id;

    @FXML
    private Label Group_name;

    @FXML
    private Button DeleteGroup;

    @FXML
    private ListView<Data> GroupUserList;

    @FXML
    private Label Group_main;

    @FXML
    private Button SendMessage;

    @FXML
    void DeleteGroup_Action(ActionEvent event) {

    }

    @FXML
    void SendMessage_Action(ActionEvent event) {

    }

    public void initialize(URL url, ResourceBundle rb) {

        GroupUserList.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {

            @Override
            public ListCell<Data> call(ListView<Data> param) {
                // TODO Auto-generated method stub
                ListCell<Data> listcell  = new ListCell<Data>(){


                    @Override
                    //只定义编辑单元格一定要重写的方法
                    protected void updateItem(Data item, boolean empty) {
                        // TODO Auto-generated method stub
                        super.updateItem(item, empty);

                        if(empty == false){
                            HBox hbox = new HBox(10);
                            hbox.setAlignment(Pos.CENTER_LEFT);

                            Image img = new Image(item.getHead());
                            ImageView Head = new ImageView();
                            Head.setImage(img);
                            Head.setPreserveRatio(true);
                            Head.setFitHeight(30);
                            Circle circle1 = new Circle( );
                            circle1.setRadius(15);
                            circle1.setCenterX(15);
                            circle1.setCenterY(15);
                            Head.setClip(circle1);

                            Label Note =new Label(item.getNote());
                            hbox.getChildren().addAll(Head,Note);
                            this.setGraphic(hbox);
                        }
                    }

                };
                return listcell;
            }
        });

        try {
            Group GroupMessage = new Group();
            String sql_Group = "select * from groupmanagement where Group_id="+group_Edit;
            GroupMessage= AddDeleteCheckChange_Group.Select_Group(sql_Group);

            Group_id.setText(GroupMessage.getGroup_id()+"");                                    //群id

            Image img = new Image(GroupMessage.getGroup_head());
            Group_head.setImage(img);                                                           //群头像

            Group_name.setText(GroupMessage.getGroup_name());                                   //群名称

            Group_main.setText(GroupMessage.getGroup_main()+"");                                //群主

            ObservableList<Data> GroupUserlist = FXCollections.observableArrayList();             //群内用户列表
            GroupUserList.setItems(GroupUserlist);
            String sql_Grouplist = "select * from grouplist where Group_id="+group_Edit;
            ResultSet rs_GroupUserlist=null;
            rs_GroupUserlist= AddDeleteCheckChange_list.Selectlist(sql_Grouplist);
            while(rs_GroupUserlist.next()) {
                Data data = new Data();
                User user = AddDeleteCheckChange_friend.Select(rs_GroupUserlist.getInt("Main_id"));
                data.setHead(user.getHeadPhoto());
                data.setNote(user.getNetName());
                GroupUserlist.add(data);
            }
            AddDeleteCheckChange_list.MysqlClose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
