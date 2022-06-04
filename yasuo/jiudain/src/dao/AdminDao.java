package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.AdminBean;
import util.DBUtil;
/**
 * 有关用户账号的连接数据库操作，登录验证，注册，修改账号，修改密码
 */
public class AdminDao {
	
	/**
	 * 登录验证功能，传入用户名和密码，在数据库中查找，如果找到了，返回true，没找到则返回false
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean Login_verify(String username,String password){
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where username=? and password=?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1,username);
			stm.setString(2,password);
			rs = stm.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return false;
	}
	/**
	 * 注册账号的函数，传入账号，密码，姓名，邮箱，手机号
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param phone
	 */
	public void Register(String username, String password, String name, String email, String phone) {
		// TODO Auto-generated method stub
				Connection conn = DBUtil.getConnectDb();
				String sql = "insert into admin(username,password,name,email,phone)" +
						"values(?,?,?,?,?)";
				int rs = 0;
				PreparedStatement stm = null;
				try {
					stm = conn.prepareStatement(sql);
					stm.setString(1, username);
					stm.setString(2, password);
					stm.setString(3, name);
					stm.setString(4, email);
					stm.setString(5, phone);
					rs = stm.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	/**
	 * 根据传入的账号，密码，来查找对应的用户信息，返回一个AdminBean类型，
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminBean getAdminInfo(String username, String password) {
		// TODO Auto-generated method stub
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where username=? and password=?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1,username);
			stm.setString(2,password);
			rs = stm.executeQuery();
			if(rs.next()){
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}

	/**
	 * 根据传入的aid，查找到对应的用户的全部信息，返回一个AdminBean类型的数据，与上一个相似，只是aid的类型为String，
	 * @param aid
	 * @return
	 */
	public AdminBean get_AidInfo2(String aid){
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid="+aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if(rs.next()){
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}
	/**
	 * 修改用户的信息，
	 */
	public void updateUser(int aid, String username, String password, String name, String email, String phone,
			int lend_num, int max_num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=?,lend_num=?,max_num=? where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, lend_num);
			stm.setInt(7, max_num);
			stm.setInt(8, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除用户的信息，根据传入的aid作为条件
	 * @param aid
	 */
	public void deleteUser(int aid) {
		// TODO Auto-generated method stub
				Connection conn = DBUtil.getConnectDb();
				String sql = "delete from admin where aid=?";
				PreparedStatement stm = null;
				try {
					stm = conn.prepareStatement(sql);
					stm.setInt(1, aid);
					stm.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}
}
