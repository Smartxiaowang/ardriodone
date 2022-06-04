package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//数据库访问类
public class DBOper {
//	Connection接口，用于连接数据库
	Connection conn=null;
	
//	Statement接口，执行sql语句并将数据检索到ResultSet中
	
//	PreparedStatement接口，Statement接口的子接口，执行预编译的SQL语句
	PreparedStatement pstmt=null;
	
//	ResultSet接口，封装Statemnet的executeQuery()方法返回的结果集，结果集接口，提供检索sql语句的方法并返回数据
	ResultSet rs=null;
	
//	CallableStatement接口，执行sql存储过程的语句
	
/*
* 1.创建数据库连接  .getConnection(server,dbname,user,pwd);
* Class.forName();
* conn=DriverManager.getConnection();
*/
	public Connection getConnection(String server,String dbname,String user,String pwd){
		try{
			String DRIVER ="com.mysql.jdbc.Driver";
			String URL="jdbc:mysql://"+"47.93.50.249"+":3306/"+"ts"+"?userUnicode=true&characterEncoding=utf8";
			
//			注册驱动,加载驱动程序，DRIVER是驱动程序名称
			Class.forName(DRIVER);
//			DriverManager数据库驱动管理类，用于加载和卸载各种驱动程序，并建立与数据库的连接
			conn=DriverManager.getConnection(URL,"admin","wangwang01");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
//		返回连接
		return conn;
	}
	
/*
* 	2.释放资源，关闭连接  .closeAll();
* 结果集rs-->载体pstmt-->连接conn
* .close();
*/
	public void closeAll(){
		try{
//			关闭结果集；如果rs不空，关闭rs
			if(rs!=null){
				rs.close();
			}
		}catch(SQLException e){
//			打印异常栈信息
			e.printStackTrace(); 
		}finally{
			try{
//				关闭载体；如果pstmt不空，关闭pstmt
				if(pstmt!=null){
					pstmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
//					关闭连接
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
/*
* 	3.执行SQL语句，可以进行查询  .executeQuery(sql,param);
* 
*/
	public ResultSet executeQuery(String preparedSql,String[] param){
//		处理SQL，执行SQL
		try{
//			得到PreparedStatement对象pstmt,将参数化的sql语句送往数据库
			pstmt=conn.prepareStatement(preparedSql);
			
			if(param!=null){
				for(int i=0;i<param.length;i++){
//					为预编译sql设置参数，setString为输入参数进行赋值
					pstmt.setString(i+1, param[i]);
				}
			}
//			执行给定的sql语句，并返回单个ResultSet对象
			rs=pstmt.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	
//	4.执行sql语句可以增删改，不查询
	public int executeUpdate(String preparedSql,String[] param){
		int num=0;
		try{
//			得到PreparedStatement对象，将参数化的sql语句送往数据库
			pstmt=conn.prepareStatement(preparedSql);
			
			if(param!=null&&param.length>0){
				for(int i=0;i<param.length;i++){
//					为预编译sql设置参数
					pstmt.setString(i+1, param[i]);
				}
			}
//			指定给定的sql语句，可能是insert update delete，或者不返回任何内容的sql语句
			num=pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return num; 
	} 
	
}

