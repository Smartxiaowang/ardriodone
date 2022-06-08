package util;

import java.sql.*;

public class DBUtil {
	/**
	 * 连接数据库的操作，用户名，密码，使用jdbc连接
	 */
	public static String username = "admin";
	public static String password = "wangwang01";
	public static String url = "jdbc:mysql://47.93.50.249:3306/ts?characterEncoding=utf-8";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnectDb(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(url,username,password);
		
		} catch (SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void CloseDB(ResultSet rs, PreparedStatement stm, Connection conn){
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stm!=null)
		{
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
