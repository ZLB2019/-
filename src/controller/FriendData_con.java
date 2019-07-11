package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.*;
import view.CLASS.windows_screen;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.ResourceBundle;

import static controller.Main_con.friend_Edit;
import static controller.Main_con.id_main;

public class FriendData_con  implements Initializable {

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
    private Hyperlink ChangeNote;

    @FXML
    private Button SendMessage;

    @FXML
    private TextField Note;

    @FXML
    private Button DeleteFriend;

    public static int friend_id;

    @FXML
    void ChangeNote_Action(ActionEvent event) {
        int firend_id=Integer.parseInt(UserName.getText());

        String sql = "update friendlist set Note='"+Note.getText()+"' where Main="+id_main+" and Friend="+firend_id;
        try {
            AddDeleteCheckChange_friend.Update(sql);
            new windows_screen(). NewWindows(new Stage(),"../FXML/ChangeSuccessful.fxml","修改成功",348, 196);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteFriend_Action(ActionEvent event) throws Exception{
        int id_fiend=0;
        id_fiend=Integer.parseInt(UserName.getText());
        String sql = "delete from friendlist where Main=" + id_main + " and Friend="+id_fiend;
        AddDeleteCheckChange_friend.Delete(sql);
         sql = "delete from friendlist where Main=" + id_fiend + " and Friend="+id_main;
        AddDeleteCheckChange_friend.Delete(sql);
        DeleteFriendSuccessful:   new windows_screen(). NewWindows(new Stage(),"../FXML/DeleteFriendSuccessful.fxml","好友删除成功",392,210);
        Stage stage;
        stage = (Stage)DeleteFriend.getScene().getWindow();
        stage.close();
    }

    @FXML
    void SendMessage_Action(ActionEvent event) {
        Stage stage = (Stage)SendMessage.getScene().getWindow();
        stage.close();
        try {
            new windows_screen(). NewWindows(new Stage(),"../FXML/Chat.fxml","chat",994,665);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        try {
            friend_id=friend_Edit;                                      //获取联系人
            ChatClient.Client_other=friend_Edit;

            Calendar now= Calendar.getInstance();                       //获取时间

            User user = AddDeleteCheckChange_friend.Select(friend_Edit);

            Image image = new Image(user.getHeadPhoto());
            Head.setImage(image);                                           //显示头像
            Circle circle1 = new Circle();
            circle1.setRadius(52);
            circle1.setCenterX(52);
            circle1.setCenterY(52);
            Head.setClip(circle1);

            UserName.setText(""+ friend_Edit);                                //显示账号

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

            String sql_Friendslist = "select * from friendlist where Main="+id_main+" and Friend="+friend_Edit;
            ResultSet rs_Friendslist=null;
            rs_Friendslist= AddDeleteCheckChange_list.Selectlist(sql_Friendslist);
            if(rs_Friendslist.next()) {
                Note.setText(rs_Friendslist.getString("Note"));
            }
            AddDeleteCheckChange_list.MysqlClose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
