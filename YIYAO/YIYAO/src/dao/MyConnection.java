package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	// 类的静态块，在类加载时运行一次
	static {
		try {
			// 驱动类只加载一次，不用每次都加载
			// 加载SQL server的驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱动类未找到");
		}
	}

	// 静态成员是类的成员不是对象的成员，所有对象共用静态成员，静态成员可以通过类名调用
	public static Connection getConnection() {
		// SQL server的连接字符串
		String url = "jdbc:sqlserver://47.93.50.249:1433;DatabaseName=YIYAO";
		try {
			return DriverManager.getConnection(url, "sa", "wangwang01!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("获取连接失败：");
			e.printStackTrace();
			// NO suitable driver found for ... //驱动类型错误
			// 通过端口 1433 连接到主机 localhost 的 TCP/IP 连接失败。 //服务器地址有误或端口号有误
			// Unknown database ... //数据库名有误
			// Access denied for user ... //用户名或密码有误
		}
		return null;
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("关闭查询语句时出错");
		}
	}
	
	public static void close(Statement st) {
		try {
			if (st != null)
				st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("关闭语句时出错");
		}
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("关闭连接异常");
		}
	}

	public static void close(ResultSet rs,Statement st, Connection conn) {
		close(rs);
		close(st);
		close(conn);		
	}	
	
	
	
	public static void close(Statement st, Connection conn) {
		close(st);
		close(conn);		
	}

	
	
}
