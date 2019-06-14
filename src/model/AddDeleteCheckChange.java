package model;

import controller.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import model.User;


public class AddDeleteCheckChange {
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

    /**增加*/
    public static void Insert(String sql)throws Exception{
            Connection conn = null;						    //数据库连接
            Statement stmt = null;							//数据库操作
            //连接MySQL数据库时，要写上连接的用户名和密码
            Class.forName(driver);							//加载驱动程序
            //连接MySQL数据库时，要写上连接的用户名和密码
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();					//实例化Statement对象
            stmt.execute(sql);								//执行数据库的操作
            stmt.close();									//数据库关闭
            conn.close();
    }
    /**查询
     * 返回一个User对象
     * */
    public static User Select(int UserName)throws Exception{
        User UserMessage = new User();
        Connection conn = null;						    //数据库连接
        Statement stmt = null;							//数据库操作
        ResultSet rs = null;                                 //保存查询结果
        String sql =sql = "select * from userinformation where Id="+UserName;
        //连接MySQL数据库时，要写上连接的用户名和密码
        Class.forName(driver);							//加载驱动程序
        //连接MySQL数据库时，要写上连接的用户名和密码
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();					            //实例化Statement对象
        rs = stmt.executeQuery(sql);								//执行数据库的操作
        if(rs.next()) {
//            System.out.println(rs.getInt("Id"));
//            System.out.println(rs.getString("Password"));
//            System.out.println(rs.getString("Mail"));
//            System.out.println(rs.getString("Signature"));
//            System.out.println(rs.getString("HeadPhoto"));

            UserMessage.setId(rs.getInt("Id"));
            UserMessage.setPassword(rs.getString("Password"));
            UserMessage.setMail(rs.getString("Mail"));
            UserMessage.setSignature(rs.getString("Signature"));
            UserMessage.setHeadPhoto(rs.getString("HeadPhoto"));
            UserMessage.setBorn(rs.getString("Born"));
            UserMessage.setSex(rs.getString("Sex"));
            UserMessage.setNetName(rs.getString("NetName"));
        }

        stmt.close();								        	//数据库关闭
        conn.close();
        return UserMessage;
    }

    /**修改*/
    public static void  Update(String sql)throws Exception{
        Connection conn = null;						    //数据库连接
        Statement stmt = null;							//数据库操作
        //连接MySQL数据库时，要写上连接的用户名和密码
        Class.forName(driver);							//加载驱动程序
        //连接MySQL数据库时，要写上连接的用户名和密码
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();					//实例化Statement对象
        stmt.execute(sql);								//执行数据库的操作
        stmt.close();									//数据             库关闭
        conn.close();
    }
}

