package view.CLASS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class windows_screen extends Application {

    Stage stage =new Stage();

    public void NewWindows(Stage primaryStage,String fxml,String Title,int w,int h) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource(fxml));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:///E:/聊天器图片素材/图标.png"));
        primaryStage.setTitle(Title);
        primaryStage.setScene(new Scene(root, w, h));
        primaryStage.show();
    }
    @Override
    public void start(Stage primaryStage){
    }
}

/**login                :   new windows_screen(). NewWindows(new Stage(),"../FXML/login.fxml","登录",600,400);
 *registered            :   new windows_screen(). NewWindows(new Stage(),"../FXML/registered.fxml","注册Chat",555,624);
 *login_No              :   new windows_screen(). NewWindows(new Stage(),"../FXML/login_No.fxml","登录失败",445,235);
 *Main                  :   new windows_screen(). NewWindows(new Stage(),"../FXML/Main.fxml","Chat",412,707);
 *registered_No         :   new windows_screen(). NewWindows(new Stage(),"../FXML/registered_No.fxml","输入有误",392,210);
 *registered_Yes        :   new windows_screen(). NewWindows(new Stage(),"../FXML/registered_Yes.fxml","注册成功",405,259);
 *RetrievePassword      :   new windows_screen(). NewWindows(new Stage(),"../FXML/RetrievePassword.fxml","找回密码",606,467);
 *ChangePassword_Ret    :   new windows_screen(). NewWindows(new Stage(),"../FXML/ChangePassword_Ret.fxml","修改密码",633,389);
 *ChangePassword_Ret    :   new windows_screen(). NewWindows(new Stage(),"../FXML/ChangePassword_Main.fxml","修改密码",633,389);
 *ChangeSuccessful      ：  new windows_screen(). NewWindows(new Stage(),"../FXML/ChangeSuccessful.fxml","修改成功",348, 196);
 *PersonalData_screen   :   new windows_screen(). NewWindows(new Stage(),"../FXML/PersonalData.fxml","个人资料",412, 690);
 *InputError            :   new windows_screen(). NewWindows(new Stage(),"../FXML/InputError.fxml","输入有误",392,210);
 *ChangePersonalData    :   new windows_screen(). NewWindows(new Stage(),"../FXML/ChangePersonalData.fxml","编辑个人资料",449,544);
 *ConfirmLogout         :   new windows_screen(). NewWindows(new Stage(),"../FXML/ConfirmLogout.fxml","确认退出",392,210);
 *CancellationOfPrompt  ：  new windows_screen(). NewWindows(new Stage(),"../FXML/CancellationOfPrompt.fxml","操作失败",392,210);
 *FriendData_Add        :   new windows_screen(). NewWindows(new Stage(),"../FXML/FriendData_Add.fxml","用户个人资料",421,690
 *FindError             :   new windows_screen(). NewWindows(new Stage(),"../FXML/FindError.fxml","查找失败",392,210);
 *FriendAddError        :   new windows_screen(). NewWindows(new Stage(),"../FXML/FriendAddError.fxml","好友添加失败",392,210);
 *FriendAddSuccessful   :   new windows_screen(). NewWindows(new Stage(),"../FXML/FriendAddSuccessful.fxml","好友添加成功",392,210);
 *CreateGroup           :   new windows_screen(). NewWindows(new Stage(),"../FXML/CreateGroup.fxml","创建群聊",432,312);
 *CreateGroupSuccessful :   new windows_screen(). NewWindows(new Stage(),"../FXML/CreateGroupSuccessful.fxml","创建群聊成功",392,210);
 *GroupData_Add         :   new windows_screen(). NewWindows(new Stage(),"../FXML/GroupData_Add.fxml","Chat群资料",421,702);
 *GroupAddError         :   new windows_screen(). NewWindows(new Stage(),"../FXML/GroupAddError.fxml","群聊加入失败",392,210);
 *GroupAddSuccessful    :   new windows_screen(). NewWindows(new Stage(),"../FXML/GroupAddSuccessful.fxml","群聊加入成功",392,210);
 *FriendData            :   new windows_screen(). NewWindows(new Stage(),"../FXML/FriendData.fxml","好友个人资料",421,710);
 *GroupData             :   new windows_screen(). NewWindows(new Stage(),"../FXML/GroupData.fxml","Chat群资料",421,702);
 *DeleteFriendSuccessful:   new windows_screen(). NewWindows(new Stage(),"../FXML/DeleteFriendSuccessful.fxml","好友删除成功",392,210);
 *Chat                  :   new windows_screen(). NewWindows(new Stage(),"../FXML/Chat.fxml","chat",994,665);
 *GroupChat             :   new windows_screen(). NewWindows(new Stage(),"../FXML/GroupChat.fxml","chat",1144,677);
 *InvitationSuccessful  :   new windows_screen(). NewWindows(new Stage(),"../FXML/InvitationSuccessful.fxml","邀请群成员成功",392,210);
 *InvitationError       :   new windows_screen(). NewWindows(new Stage(),"../FXML/InvitationError.fxml","邀请群成员失败",392,210);
 *DissolutionGroupSuccessful :   new windows_screen(). NewWindows(new Stage(),"../FXML/DissolutionGroupSuccessful.fxml","解散成功",392,210);
 *ExitGroupSuccessful   ;   new windows_screen(). NewWindows(new Stage(),"../FXML/ExitGroupSuccessful.fxml","退出成功",392,210);
 */

