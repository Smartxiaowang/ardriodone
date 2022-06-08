package com.edu.entity;

import com.edu.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CommandDao {
    //	获取当前电影的所有评论
    public List<Command> getCommand(int mId, int uId) {
        List<Command> lst = new ArrayList<Command>();

        String sql2 = " SELECT\n" +
                "\tadmin.name, \n" +
                "\tcId, \n" +
                "\tcWord, \n" +
                "\tcTime, \n" +
                "\tc.uId, \n" +
                "\tc.mId\n" +
                "FROM\n" +
                "\tcomments AS c\n" +
                "\tLEFT JOIN\n" +
                "\tadmin\n" +
                "\tON \n" +
                "\tc.uId = admin.aid \n" +
                " WHERE mId =" + mId + " ORDER BY " +
                " cTime desc";
        // AND uId ="+uId+"
        try {
            Connection c = new DBUtil().getConnectDb();
            PreparedStatement preparedStatement = c.prepareStatement(sql2);
            ResultSet rs2 = preparedStatement.executeQuery(sql2);
            while (rs2.next()) {
                Command command = new Command();
                command.setcId(rs2.getInt("cId"));
                command.setuId(rs2.getInt("uId"));
                command.setmId(rs2.getInt("mId"));
                command.setcWord(rs2.getString("cWord"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String cTime = dateFormat.format(rs2.getDate("cTime"));
                command.setcTime(cTime);
                command.setuName(rs2.getString("name"));
                lst.add(command);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lst;
    }

    //	添加评论
    public boolean add(int uId, int mId, String cWord, String cTime) {
//		cId自动生成
        String sql = "insert into comments(uId,mId,cWord,cTime) values(" + uId + "," + mId + ",'" + cWord + "','" + cTime + "')";

        try {
            Connection c = new DBUtil().getConnectDb();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            int res = preparedStatement.executeUpdate(sql);
            c.close();
            if (res > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //	删除评论
    public boolean delete(String cId) {
        String sql = "delete from comments where cId = " + cId;
        try {
            Connection c = new DBUtil().getConnectDb();
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            int res = preparedStatement.executeUpdate(sql);
            if (res > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
