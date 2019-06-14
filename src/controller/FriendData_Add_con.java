package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AddDeleteCheckChange;
import model.AddDeleteCheckChange_list;
import model.User;
import view.CLASS.windows_screen;


import java.awt.datatransfer.FlavorEvent;
import java.beans.IntrospectionException;
import java.net.URL;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ResourceBundle;

import static controller.Main_con.id_Find;
import static controller.Main_con.id_main;

public class FriendData_Add_con implements Initializable {

    @FXML
    private ImageView Head;

    @FXML
    private Label UserName;

    @FXML
    private Label Signature;

    @FXML
    private Label Born;

    @FXML
    private Label Sex;

    @FXML
    private Label NetName;

    @FXML
    private Label Age;

    @FXML
    private Button AddFriend;

    @FXML
    void AddFriend_Action(ActionEvent event) throws Exception{
        String sql =sql = "select * from friendlist where Main="+id_main;
        ResultSet rs=null;
        boolean flag = false;
        rs= AddDeleteCheckChange_list.Selectlist(sql);

        if(id_Find==id_main)            //不能加自己
            flag=true;

        while(rs.next()) {              //不能加已经存在的好友
            if (rs.getInt("Friend") == id_Find) {
                flag = true;
                break;
            }
        }
        AddDeleteCheckChange_list.MysqlClose();             //关闭数据库连接
        /**添加好友失败*/
        if(flag){
            new windows_screen(). NewWindows(new Stage(),"../FXML/FriendAddError.fxml","好友添加失败",392,210);
            Stage stage;
            stage = (Stage)AddFriend.getScene().getWindow();
            stage.close();
            return;
        }

        sql = "insert into friendlist(Main,Friend,Note)"
                +" values(+"+id_main+","+id_Find+","+id_Find+")";
        /**添加好友成功*/
        AddDeleteCheckChange.Insert(sql);
        new windows_screen(). NewWindows(new Stage(),"../FXML/FriendAddSuccessful.fxml","好友添加成功",392,210);
        Stage stage;
        stage = (Stage)AddFriend.getScene().getWindow();
        stage.close();


    }

    public void initialize(URL url, ResourceBundle rb) {

        try {
            Calendar now= Calendar.getInstance();                       //获取时间

            User user = AddDeleteCheckChange.Select(id_Find);

            Image image = new Image(user.getHeadPhoto());
            Head.setImage(image);                                           //显示头像

            UserName.setText(""+ id_Find);                           //显示账号

            NetName.setText(user.getNetName());                         //显示网名

            int age = 0;
            /**Calendar.MONTH 的取值是 0~11 所以表示真实月份要‘+1’*/
            age = now.get(Calendar.YEAR)-Integer.parseInt(user.getBorn().substring(0,4));
            if(now.get(Calendar.MONTH)+1<Integer.parseInt(user.getBorn().substring(5,7))||(now.get(Calendar.MONTH)+1==Integer.parseInt(user.getBorn().substring(5,7))&&now.get(Calendar.DAY_OF_MONTH)<Integer.parseInt(user.getBorn().substring(8,10))))
                age--;
            Age.setText(""+age);

            Sex.setText(user.getSex());

            Born.setText(user.getBorn());

            Signature.setText(user.getSignature());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
