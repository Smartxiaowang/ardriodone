package entity;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OpenUpDao
 * @Date 9:42 2022/6/5
 * @Version 1.0
 **/
public class OpenUpDao {

    public List<OpenUp> getOpenUpList() {
        ArrayList<OpenUp> dataList = new ArrayList<>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from openup";
        ResultSet rs;
        PreparedStatement stm;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                OpenUp openUp = new OpenUp();
                openUp.setId(rs.getString("id"));
                openUp.setTitle(rs.getString("title"));
                openUp.setImgsrc(rs.getString("imgsrc"));
                openUp.setColumn(rs.getString("column"));
                dataList.add(openUp);
            }
            conn.close();
        } catch (Exception e) {

        }
        return dataList;
    }

}
