package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddDeleteCheckChange_Group extends AddDeleteCheckChange_friend{

    //定义MySQL数据库的驱动程序
    public static final String driver="com.mysql.cj.jdbc.Driver";

    /**@定义MySQL数据库的连接地址
    /**serverTimezone=GMT : 是为了消除关于‘时区’的错误
     * 由于MySQL在高版本需要指明是否进行SSL(保障Internet数据传输安全利用数据加密)，所以要加下面这行代码消除警告
     *        useSSL=false ：是为了消除关于 ‘是否进行SSL’的警告
     * */
    public static final String url = "jdbc:mysql://localhost:3306/user?serverTimezone=GMT&useSSL=false";

    //定义MySQL数据库的连接用户名
    public static final String user="root";
    //定义MySQL数据库的连接密码
    public static final String pass="zlb19991111";

    /**查询
     * 返回一个bool值
     * */
    public static boolean Select_bool(String sql)throws Exception{
        boolean flag = false;
        User UserMessage = new User();
        Connection conn = null;						    //数据库连接
        Statement stmt = null;							//数据库操作
        ResultSet rs = null;                                 //保存查询结果
        //连接MySQL数据库时，要写上连接的用户名和密码
        Class.forName(driver);							//加载驱动程序
        //连接MySQL数据库时，要写上连接的用户名和密码
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();					            //实例化Statement对象
        rs = stmt.executeQuery(sql);								//执行数据库的操作
        if(rs.next()) {
            flag = true;
        }
        stmt.close();								        	//数据库关闭
        conn.close();
        return flag;
    }

    /**查询
     * 返回一个Group值
     * */
    public static Group Select_Group(String sql)throws Exception{
        boolean flag = false;
        Group GroupMessage = new Group();
        Connection conn = null;						    //数据库连接
        Statement stmt = null;							//数据库操作
        ResultSet rs = null;                                 //保存查询结果
        //连接MySQL数据库时，要写上连接的用户名和密码
        Class.forName(driver);							//加载驱动程序
        //连接MySQL数据库时，要写上连接的用户名和密码
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();					            //实例化Statement对象
        rs = stmt.executeQuery(sql);								//执行数据库的操作
        if(rs.next()) {
//            System.out.println(rs.getInt("Group_id"));
//            System.out.println(rs.getInt("Group_main"));
//            System.out.println(rs.getString("Group_head"));
//            System.out.println(rs.getString("Group_name"));

            GroupMessage.setGroup_id(rs.getInt("Group_id"));
            GroupMessage.setGroup_main(rs.getInt("Group_main"));
            GroupMessage.setGroup_head(rs.getString("Group_head"));
            GroupMessage.setGroup_name(rs.getString("Group_name"));
        }
        stmt.close();								        	//数据库关闭
        conn.close();
        return GroupMessage;
    }

}
