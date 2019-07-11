package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import view.CLASS.windows_screen;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static controller.Main_con.*;

public class GroupData_con implements Initializable {

    @FXML
    private ImageView Group_head;

    @FXML
    private Hyperlink ChangeHead;

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
    void DeleteGroup_Action(ActionEvent event) throws Exception {
        if(DeleteGroup.getText().equals("解散该群")){
            String sql = "delete from groupmanagement where Group_id=" + group_Edit;
            AddDeleteCheckChange_Group.Delete(sql);
            sql  = "delete from chat where Friend=" + group_Edit;
            AddDeleteCheckChange_Group.Delete(sql);
            sql  = "delete from grouplist where Group_id=" + group_Edit;
            AddDeleteCheckChange_Group.Delete(sql);
            new windows_screen(). NewWindows(new Stage(),"../FXML/DissolutionGroupSuccessful.fxml","解散成功",392,210);
        }else{
            String sql = "delete from grouplist where Main_id=" + id_main + " and Group_id="+group_Edit;
            AddDeleteCheckChange_Group.Delete(sql);
            new windows_screen(). NewWindows(new Stage(),"../FXML/ExitGroupSuccessful.fxml","退出成功",392,210);
        }

    }

    @FXML
    void ChangeHead_Action(ActionEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");			//打开文件窗口  名称
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Photo Files", "*.jpg", "*.png","*.bmp"));
        /**null———当前电脑显示器屏幕的中央。
         *this———当前你编写的程序屏幕中央
         * 如果是你其他的 控件名称 就是以这个 控件 为中心，弹出的文件选择器。
         **/
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String HeadPath = "file:///";
            String sql;
            HeadPath = HeadPath+file.getAbsolutePath();
            HeadPath=HeadPath.replace('\\','/');
            sql="update groupmanagement set Group_head='"+HeadPath+"' where Group_id="+ group_Edit;
            try {
                AddDeleteCheckChange_friend.Update(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sql_Group = "select * from groupmanagement where Group_id="+group_Edit;
            Group GroupMessage = AddDeleteCheckChange_Group.Select_Group(sql_Group);
            Image img = new Image(GroupMessage.getGroup_head());
            Group_head.setImage(img);                                                           //群头像
        }
    }

    @FXML
    void SendMessage_Action(ActionEvent event) {
        Stage stage = (Stage)SendMessage.getScene().getWindow();
        stage.close();
        try {
            new windows_screen(). NewWindows(new Stage(),"../FXML/GroupChat.fxml","chat",1144,677);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {

        ChatClient.Client_other=group_Edit;

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
                        }else if(empty){
                            setText(null);
                            setGraphic(null);
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
            if(GroupMessage.getGroup_main() == id_main) {
                ChangeHead.setVisible(true);
                DeleteGroup.setText("解散该群");
            }else{
                ChangeHead.setVisible(false);
            }


            Group_id.setText(GroupMessage.getGroup_id()+"");                                    //群id

            Image img = new Image(GroupMessage.getGroup_head());
            Group_head.setImage(img);                                                           //群头像
            Circle circle1 = new Circle();
            circle1.setRadius(52);
            circle1.setCenterX(52);
            circle1.setCenterY(52);
            Group_head.setClip(circle1);

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
