package db;

import java.sql.*;
import java.util.ResourceBundle;

public class DatabaseUtil {
    Connection conn = null;
    public  DatabaseUtil() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//注册驱动
        this.conn = DriverManager.getConnection("jdbc:mysql://47.93.50.249:3306/web003?serverTimezone=GMT%2B8","admin","wangwang01");
        System.out.println(conn);
    }
    public  void  showConnect(){
        if (conn==null){
            System.out.println("数据库连接失败");
        }else {
            System.out.println("数据库连接成功");
        }

    }
    //关闭链接
    public  void close() throws SQLException {
        conn.close();
    }
    private static final String DRIVER_CLASS;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    private static final ResourceBundle rb = ResourceBundle.getBundle("db.dbconfig");

    /**
     * 静态代码块，用于从属性文件读取数据库连接用用户名、密码、驱动类和数据库url
     */
    static {
        URL = rb.getString("jdbc.url");
        USERNAME = rb.getString("jdbc.username");
        PASSWORD = rb.getString("jdbc.password");
        DRIVER_CLASS = rb.getString("jdbc.driver");
    }

    public static Connection getConnection() {

        Connection con = null;

        try {
            Class.forName(DRIVER_CLASS);
            con= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return con;
    }

    public static void close(ResultSet rs, Statement stmt, Connection con){
        try {
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(con!=null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
