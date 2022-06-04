package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.TypeBean;
import util.DBUtil;
/**
 * 分类的类
 */
public class TypeDao {

	/**
	 * 获取所有消费类型的信息，返回数组形式
	 * @return
	 */
	public ArrayList<TypeBean> get_ListInfo(){
		ArrayList<TypeBean> tag_Array = new ArrayList<TypeBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from shoptype";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				TypeBean tag = new TypeBean();
				tag.setTid(rs.getInt("tid"));
				tag.setName(rs.getString("name"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}

}
