package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.LoginDao;
import dao.MyConnection;
import bean.LoginBean;

public class LoginDaoImpl implements LoginDao {

	@Override
	public int insert(LoginBean login) {
		
		return 0;
	}

	@Override
	public int delete(LoginBean login) {
		
		return 0;
	}

	@Override
	public int delete(int loginId) {
	
		return 0;
	}

	@Override
	public int update(LoginBean login) {
	
		return 0;
	}

	@Override
	public List<LoginBean> select() {
		
		return null;
	}

	//通过登录名获取登陆对象，用于实现登录功能
	@Override
	public LoginBean select(String loginName) {
		Connection conn=null;
		PreparedStatement  ps=null;
		ResultSet rs=null;
		String strsql="select * from Logins where LoginName=?";
				
		try {
			conn=MyConnection.getConnection();
			ps=conn.prepareStatement(strsql);
			ps.setString(1, loginName);
			
			
			rs=ps.executeQuery();
			if(rs.next()){
			LoginBean login =new LoginBean();
			int count=1;
	login.setLoginId(rs.getInt(count++));
	login.setLoginName(rs.getString(count++));
	login.setPassword(rs.getString(count++));
	login.setEmail(rs.getString(count++));
	login.setRegisterDateTime(rs.getTimestamp(count++));
	login.setRegisterIp(rs.getString(count++));
	login.setLastLoginDateTime(rs.getTimestamp(count++));
	login.setLastLoginIp(rs.getString(count++));
	login.setLoginTime(rs.getInt(count++));
	login.setLoginType(rs.getString(count++));
				
				return login;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("查询登录信息时出错。");			
		}finally{			
			MyConnection.close(rs,ps,conn);
			
		}
		
		return null;
	}

	@Override
	public LoginBean select(int loginId) {
		
		return null;
	}

}
