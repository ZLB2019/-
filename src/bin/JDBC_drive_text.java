package bin;

public class JDBC_drive_text {
    public static void main(String[] args){
        try{ //加载MySql的驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
           // System.out.println("OK123");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }
    }
}
