package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DBOper;

public class MovieDao extends DBOper{
	//获取电影基本信息
	public  Movie getMovie(int mId){
		String sql = "select * from movie where mId="+mId;
		Movie m = new Movie();
		try {
			ResultSet rs = this.executeQuery(sql,new String[] {});
			if(rs.next()){		
				m.setmId(rs.getInt("mId"));
				m.setmDir(rs.getString("mDir"));
				m.setmScr(rs.getString("mScr"));
				m.setmAct(rs.getString("mAct"));
				m.setmType(rs.getString("mType"));
				m.setmCountry(rs.getString("mCountry"));
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String mTime=dateFormat.format(rs.getDate("mTime"));
				m.setmTime(mTime);
				m.setmLanguage(rs.getString("mLanguage"));
				m.setmIntro(rs.getString("mIntro"));
				m.setmName(rs.getString("mName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	
}
