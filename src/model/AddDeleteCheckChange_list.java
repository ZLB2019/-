package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddDeleteCheckChange_list{
    public static final String driver="com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/user?serverTimezone=GMT&useSSL=false";

    //定义MySQL数据库的连接用户名
    public static final String user="root";
    //定义MySQL数据库的连接密码
    public static final String pass="zlb19991111";
    private static Connection conn = null;						    //数据库连接
    private static Statement stmt = null;							//数据库操作

    /**查询、
     * 返回一个列表
     * */
    public static ResultSet Selectlist(String sql)throws Exception{
        ResultSet rs;                                 //保存查询结果

        User UserMessage = null;
        rs = null;
        //连接MySQL数据库时，要写上连接的用户名和密码
        Class.forName(driver);							//加载驱动程序
        //连接MySQL数据库时，要写上连接的用户名和密码
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();					            //实例化Statement对象
        rs = stmt.executeQuery(sql);								//执行数据库的操作
        return  rs;
    }

    public static void MysqlClose() throws  Exception{
        try {
        } finally {
            stmt.close();								        	//数据库关闭
            conn.close();
        }
    }
}
