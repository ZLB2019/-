package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AddDeleteCheckChange_friend;
import model.User;
import view.CLASS.windows_screen;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import static controller.Main_con.id_main;

public class PersonalData_con implements Initializable {

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
    private Hyperlink ChangePassword;

    @FXML
    private Hyperlink ChangeData;

    @FXML
    private Hyperlink ChangeHead;

//    void JT() throws Exception{
//        ChangePersonalData_con.PersonalDataSuccessful.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                System.out.print("监听到窗口关闭");
//            }
//        });
//    }

    @FXML
    void ChangeData_Action(ActionEvent event) {
        /**如果用户已经注销,则显示 ‘操作失败’ 窗口 ，阻止正常的弹出窗口*/
        if(Main_con.id_main==0){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/CancellationOfPrompt.fxml","操作失败",392,210);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            new windows_screen().NewWindows(new Stage(),"../FXML/ChangePersonalData.fxml","编辑个人资料",449,544);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ChangeHead_Action(ActionEvent event) {
        /**如果用户已经注销,则显示 ‘操作失败’ 窗口 ，阻止正常的弹出窗口*/
        if(Main_con.id_main==0){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/CancellationOfPrompt.fxml","操作失败",392,210);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
            int i;
            HeadPath = HeadPath+file.getAbsolutePath();
            HeadPath=HeadPath.replace('\\','/');
            sql="update userinformation set HeadPhoto='"+HeadPath+"' where Id="+ id_main;
            try {
                AddDeleteCheckChange_friend.Update(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void ChangePassword_Action(ActionEvent event) {
        /**如果用户已经注销,则显示 ‘操作失败’ 窗口 ，阻止正常的弹出窗口*/
        if(Main_con.id_main==0){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/CancellationOfPrompt.fxml","操作失败",392,210);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            new windows_screen(). NewWindows(new Stage(),"../FXML/ChangePassword_Main.fxml","修改密码",633,389);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**刷新*/
    @FXML
    void Refresh(MouseEvent event) {
        /**如果用户已经注销,则显示 ‘操作失败’ 窗口 ，阻止正常的弹出窗口*/
        if(Main_con.id_main==0){
            try {
                new windows_screen(). NewWindows(new Stage(),"../FXML/CancellationOfPrompt.fxml","操作失败",392,210);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Calendar now= Calendar.getInstance();                       //获取时间

            User user = AddDeleteCheckChange_friend.Select(id_main);

            Image image = new Image(user.getHeadPhoto());
            Head.setImage(image);                                           //显示头像

            UserName.setText(""+ id_main);                           //显示账号

            NetName.setText(user.getNetName());                         //显示网名

            int age = 0;
            /**Calendar.MONTH 的取值是 0~11 所以表示真实月份要‘+1’*/
            age = now.get(Calendar.YEAR)-Integer.parseInt(user.getBorn().substring(0,4));
            if(now.get(Calendar.MONTH)+1<Integer.parseInt(user.getBorn().substring(5,7))||(now.get(Calendar.MONTH)+1==Integer.parseInt(user.getBorn().substring(5,7))&&now.get(Calendar.DAY_OF_MONTH)<Integer.parseInt(user.getBorn().substring(8,10))))
                age--;
            Age.setText(""+age);                                                //显示年龄

            Sex.setText(user.getSex());                                             //显示性别

            Born.setText(user.getBorn());                                        //显示出生日期

            Signature.setText(user.getSignature());                                      //显示个性签名
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initialize(URL url, ResourceBundle rb) {
        try {
            Calendar now= Calendar.getInstance();                       //获取时间

            User user = AddDeleteCheckChange_friend.Select(id_main);

            Image image = new Image(user.getHeadPhoto());
            Head.setImage(image);                                           //显示头像

            UserName.setText(""+ id_main);                           //显示账号

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
