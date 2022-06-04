package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//���ݿ������
public class DBOper {
//	Connection�ӿڣ������������ݿ�
	Connection conn=null;
	
//	Statement�ӿڣ�ִ��sql��䲢�����ݼ�����ResultSet��
	
//	PreparedStatement�ӿڣ�Statement�ӿڵ��ӽӿڣ�ִ��Ԥ�����SQL���
	PreparedStatement pstmt=null;
	
//	ResultSet�ӿڣ���װStatemnet��executeQuery()�������صĽ������������ӿڣ��ṩ����sql���ķ�������������
	ResultSet rs=null;
	
//	CallableStatement�ӿڣ�ִ��sql�洢���̵����
	
/*
* 1.�������ݿ�����  .getConnection(server,dbname,user,pwd);
* Class.forName();
* conn=DriverManager.getConnection();
*/
	public Connection getConnection(String server,String dbname,String user,String pwd){
		try{
			String DRIVER ="com.mysql.jdbc.Driver";
			String URL="jdbc:mysql://"+"47.93.50.249"+":3306/"+"ts"+"?userUnicode=true&characterEncoding=utf8";
			
//			ע������,������������DRIVER��������������
			Class.forName(DRIVER);
//			DriverManager���ݿ����������࣬���ڼ��غ�ж�ظ����������򣬲����������ݿ������
			conn=DriverManager.getConnection(URL,"admin","wangwang01");

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
//		��������
		return conn;
	}
	
/*
* 	2.�ͷ���Դ���ر�����  .closeAll();
* �����rs-->����pstmt-->����conn
* .close();
*/
	public void closeAll(){
		try{
//			�رս���������rs���գ��ر�rs
			if(rs!=null){
				rs.close();
			}
		}catch(SQLException e){
//			��ӡ�쳣ջ��Ϣ
			e.printStackTrace(); 
		}finally{
			try{
//				�ر����壻���pstmt���գ��ر�pstmt
				if(pstmt!=null){
					pstmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
//					�ر�����
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
	
/*
* 	3.ִ��SQL��䣬���Խ��в�ѯ  .executeQuery(sql,param);
* 
*/
	public ResultSet executeQuery(String preparedSql,String[] param){
//		����SQL��ִ��SQL
		try{
//			�õ�PreparedStatement����pstmt,����������sql����������ݿ�
			pstmt=conn.prepareStatement(preparedSql);
			
			if(param!=null){
				for(int i=0;i<param.length;i++){
//					ΪԤ����sql���ò�����setStringΪ����������и�ֵ
					pstmt.setString(i+1, param[i]);
				}
			}
//			ִ�и�����sql��䣬�����ص���ResultSet����
			rs=pstmt.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	
//	4.ִ��sql��������ɾ�ģ�����ѯ
	public int executeUpdate(String preparedSql,String[] param){
		int num=0;
		try{
//			�õ�PreparedStatement���󣬽���������sql����������ݿ�
			pstmt=conn.prepareStatement(preparedSql);
			
			if(param!=null&&param.length>0){
				for(int i=0;i<param.length;i++){
//					ΪԤ����sql���ò���
					pstmt.setString(i+1, param[i]);
				}
			}
//			ָ��������sql��䣬������insert update delete�����߲������κ����ݵ�sql���
			num=pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return num; 
	} 
	
}

