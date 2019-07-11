package bin;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;

import java.sql.*;

public class judge {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/user?serverTimezone=GMT&useSSL=false";
    private static final String user = "root";
    private static final String pass = "zlb19991111";
    private static final String sql = "select id,password from userinformation";
    public static boolean judge(int id,String password )throws Exception {
        boolean flag = false;                                           //登录信息是否正确，默认：不正确
        Connection conn = null;                                 //数据库连接
        Statement stmt = null;                                  //数据库操作
        ResultSet rs = null;                                       //保存查询结果
        Class.forName(driver);                                    //加载驱动程序
        conn = DriverManager.getConnection(url, user, pass);         //连接MySQL数据库时，要写上连接的用户名和密码
        stmt = conn.createStatement();				        	//实例化Statement对象
        rs = stmt.executeQuery(sql);                            //实例化 ResultSet 对象

        while( rs.next() ) {                                               //指针向下移动
            int id_tmp = rs.getInt("id");                     //获得账号
            if(id_tmp == id) {                                           //找到账号
                String password_tmp = rs.getString("password");     //获得密码
                if(password_tmp.equals(password)){                                   //密码正确
                    flag = true;                                        //登录信息修改为正确，并跳出循环
                    System.out.println();
                    break;
                }
            }
        }
        return flag;
    }
}
