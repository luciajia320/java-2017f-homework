/*import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
    public static void main(String[] args) {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=test";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
        String Name="user";
        String Pwd="123";
        try
        {
            Class.forName(driverName);
            Connection conn=DriverManager.getConnection(dbURL,Name,Pwd);
            System.out.println("连接数据库成功");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("连接失败");
        }
    }
}
*/
