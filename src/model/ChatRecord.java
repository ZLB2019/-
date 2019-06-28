package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ChatRecord {
    //定义MySQL数据库的驱动程序
    public static final String driver="com.mysql.cj.jdbc.Driver";
    //定义MySQL数据库的连接地址
    public static final String url = "jdbc:mysql://localhost:3306/user?serverTimezone=GMT&useSSL=false";
    //定义MySQL数据库的连接用户名
    public static final String user="root";
    //定义MySQL数据库的连接密码
    public static final String pass="zlb19991111";

    public static void mysqlc(String main,String friend,String message,String time) throws Exception {
        Connection conn = null;							//数据库连接
        Statement stmt = null;							//数据库操作
        String sql ="insert into chat(Main,Friend,Message,Time)"
                +" values('"+main+"','"+friend+"','"+message+"','"+time+"')";

        //连接MySQL数据库时，要写上连接的用户名和密码
        Class.forName(driver);							//加载驱动程序
        //连接MySQL数据库时，要写上连接的用户名和密码
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();					//实例化Statement对象
        stmt.execute(sql);								//执行数据库的操作
        stmt.close();									//数据库关闭
        conn.close();
    }
}
