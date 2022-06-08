package com.edu.entity;

import com.edu.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    //获取电影基本信息
    public Movie getMovie(int mId) {
        String sql = "select * from movie where mId=" + mId;
        Connection c = new DBUtil().getConnectDb();

        Movie m = new Movie();
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            if (rs.next()) {
                m.setmId(rs.getInt("mId"));
                m.setmDir(rs.getString("mDir"));
                m.setmScr(rs.getString("mScr"));
                m.setmAct(rs.getString("mAct"));
                m.setmType(rs.getString("mType"));
                m.setmCountry(rs.getString("mCountry"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String mTime = dateFormat.format(rs.getDate("mTime"));
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
	//获取所有电影基本信息
    public List<Movie> getMovieList() {
        String sql = "select * from movie ";
        Connection c = new DBUtil().getConnectDb();

        List<Movie> mdataList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            while (rs.next()) {
                Movie m = new Movie();
                m.setmId(rs.getInt("mId"));
                m.setmDir(rs.getString("mDir"));
                m.setmImg(rs.getString("mImg"));
                m.setmScr(rs.getString("mScr"));
                m.setmAct(rs.getString("mAct"));
                m.setmType(rs.getString("mType"));
                m.setmCountry(rs.getString("mCountry"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String mTime = dateFormat.format(rs.getDate("mTime"));
                m.setmTime(mTime);
                m.setmLanguage(rs.getString("mLanguage"));
                m.setmIntro(rs.getString("mIntro"));
                m.setmName(rs.getString("mName"));
                mdataList.add(m);
            }
            c.close();
            return mdataList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mdataList;
    }

}
