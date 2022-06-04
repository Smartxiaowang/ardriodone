package dao;

import bean.ShoppingBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 关于消费连接数据库的所有操作的类
 */
public class ShoppingDao {

    /*
     * @Description: 消费数据添加
     * @Param: [shoppingBean]
     * @Return: void
     */
    public void addshopinfo(ShoppingBean shoppingBean) {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into shopsheet(name,type,dollers,datetime,aid) values(?,?,?,?,?)";
        int rs = 0;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, shoppingBean.getName());
            stm.setString(2, shoppingBean.getType());
            stm.setString(3, shoppingBean.getDollers());
            stm.setString(4, shoppingBean.getDatetime());
            stm.setString(5, shoppingBean.getAid());
            rs = stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*
     * @Description: 查询消费数据
     * @Param: [name, date]
     * @Return: java.util.ArrayList<bean.ShoppingBean>
     */
    public ArrayList<ShoppingBean> getShopList(String name, String date, String aid) {

        ArrayList<ShoppingBean> dataList = new ArrayList<>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "SELECT\n" +
                "\tadmin.username as aname, \n" +
                "\tshopsheet.bid, \n" +
                "\tshopsheet.`name`, \n" +
                "\tshopsheet.dollers, \n" +
                "\tshopsheet.datetime, \n" +
                "\tshoptype.`name` AS typename\n" +
                "FROM\n" +
                "\tshopsheet\n" +
                "\tINNER JOIN\n" +
                "\tshoptype\n" +
                "\tON \n" +
                "\t\tshopsheet.type = shoptype.tid\n" +
                "\tINNER JOIN\n" +
                "\tadmin\n" +
                "\tON \n" +
                "\t\tshopsheet.aid = admin.aid\n" +
                "\t\twhere shopsheet.aid = ?";
        ResultSet rs;
        PreparedStatement stm = null;
        try {

            if (name != null && !name.equals("")) {
                sql = sql + " and shopsheet.name like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, aid);

                stm.setString(2, "%" + name + "%");
            } else if (date != null && !date.equals("")) {
                sql = sql + " and shopsheet.datetime = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, aid);
                stm.setString(2, date);
            } else {
                stm = conn.prepareStatement(sql);
                stm.setString(1, aid);
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                ShoppingBean shoppingBean = new ShoppingBean();
                shoppingBean.setAname(rs.getString("aname"));
                shoppingBean.setName(rs.getString("name"));
                shoppingBean.setBid(rs.getString("bid"));
                shoppingBean.setDatetime(rs.getString("datetime"));
                shoppingBean.setTypename(rs.getString("typename"));
                shoppingBean.setDollers(rs.getString("dollers"));
                dataList.add(shoppingBean);
            }

            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dataList;
    }

    /*
     * @Description: 修改消费信息
     * @Param: [shoppingBean]
     * @Return: void
     */
    public void updateShoopInfo(ShoppingBean shoppingBean) {
        Connection conn = DBUtil.getConnectDb();
        String sql = "update shopsheet set name = ? ," +
                "type = ?," +
                " dollers = ?,datetime = ? where bid = ?";
        ResultSet rs;
        PreparedStatement stm;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, shoppingBean.getName());
            stm.setString(2, shoppingBean.getType());
            stm.setString(3, shoppingBean.getDollers());
            stm.setString(4, shoppingBean.getDatetime());
            stm.setString(5, shoppingBean.getBid());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
        }

    }

    /*
     * @Description: 获取统计信息
     * @Param: [date, type, aid]
     * @Return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String, String> getCountInfo(String date, String type, String aid) {
        if (type.equals("")) {
            return null;
        }
        if (date.equals("")) {
            return null;
        }
        String sql = "SELECT\n" +
                "\tsum(dollers) AS alldollers, \n" +
                "\tadmin.username, \n" +
                "\tshoptype.`name` AS typename\n" +
                "FROM\n" +
                "\tshopsheet\n" +
                "\tINNER JOIN\n" +
                "\tadmin\n" +
                "\tON \n" +
                "\t\tshopsheet.aid = admin.aid\n" +
                "\tINNER JOIN\n" +
                "\tshoptype\n" +
                "\tON \n" +
                "\t\tshopsheet.type = shoptype.tid\n" +
                "WHERE\n" +
                "\tshopsheet.aid = ? AND\n" +
                "\tdatetime LIKE ? \n" +
                "GROUP BY\n" +
                "\ttype\n" +
                "HAVING\n" +
                "\ttype = ?";
        Connection conn = DBUtil.getConnectDb();
        ResultSet rs;
        PreparedStatement stm;
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, aid);
            stm.setString(2, date + "%");
            stm.setString(3, type);
            rs = stm.executeQuery();
            HashMap<String, String> count = new HashMap<>();
            while (rs.next()) {
                count.put("count", rs.getString("alldollers"));
                count.put("username", rs.getString("username"));
                count.put("date", date);
                count.put("type", rs.getString("typename"));
            }
            conn.close();
            return count;

        } catch (Exception e) {
            return null;
        }
    }

    /*
     * @Description: 删除消费信息
     * @Param: [bid]
     * @Return: void
     */
    public void deleteshopInfo(int bid) {
        Connection conn = DBUtil.getConnectDb();
        String sql = "delete from shopsheet where bid=?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, bid);
            stm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
