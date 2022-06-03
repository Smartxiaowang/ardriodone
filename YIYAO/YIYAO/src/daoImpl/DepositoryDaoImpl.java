package daoImpl;

import bean.DepositoryAndYpBean;
import dao.DepositoryDao;
import dao.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DepositoryDaoImpl implements DepositoryDao {


    @Override
    public List<DepositoryAndYpBean> select(String txtSearchNumber) {
        List<DepositoryAndYpBean> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String strsql = "SELECT * " +
                "FROM\n" +
                "depository\n" +
                "\tINNER JOIN\n" +
                " orders " +
                "\tON \n" +
                "\t depository.depository_yp = orders.UId ";

        try {
            conn = MyConnection.getConnection();
            if (!txtSearchNumber.equals("")) {
                strsql = strsql + " WHERE depository.depository_name like ?";
                ps = conn.prepareStatement(strsql);
                ps.setString(1, "%" + txtSearchNumber + "%");
            }else {
                ps = conn.prepareStatement(strsql);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                DepositoryAndYpBean d = new DepositoryAndYpBean();
                d.setDepository_name(rs.getString("depository_name"));
                d.setDepository_id(rs.getString("depository_id"));
                d.setDepository_readme(rs.getString("depository_readme"));
                d.setDepository_arr(rs.getString("depository_arr"));
                d.setType(rs.getString("Type"));
                d.setuId(Integer.parseInt(rs.getString("Uid")));
                d.setName(rs.getString("Name"));
                d.setNumber(rs.getString("Number"));
                d.setDescription(rs.getString("Description"));
                list.add(d);
            }
            return list;

        } catch (Exception e) {

        }


        return null;
    }

    @Override
    public int insert(DepositoryAndYpBean d) {
        Connection conn = null;
        PreparedStatement ps = null;
        String strsql = "insert into depository values(?,?,?,?)";//可以带参数的语句

        try {
            conn = MyConnection.getConnection();
            ps = conn.prepareStatement(strsql);
            ps.setString(1, d.getDepository_name());
            ps.setString(2, d.getDepository_arr());
            ps.setString(3, d.getDepository_readme());
            ps.setString(4, d.getDepository_yp());
            return ps.executeUpdate();
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public int delete(int uId) {
        Connection conn = null;
        PreparedStatement ps = null;//可预处理的语句
        String strsql = "delete from depository where depository_id=? ";//可以带参数的语句
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strsql);

            ps.setInt(1, uId);

            return ps.executeUpdate();
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public DepositoryAndYpBean selectById(int uId) {
        Connection conn = null;
        PreparedStatement ps = null;//可预处理的语句
        String strsql = "select * from depository where depository_id=? ";//可以带参数的语句
        try {
            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strsql);

            ps.setInt(1, uId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DepositoryAndYpBean d = new DepositoryAndYpBean();
                d.setDepository_name(rs.getString("depository_name"));
                d.setDepository_id(rs.getString("depository_id"));
                d.setDepository_readme(rs.getString("depository_readme"));
                d.setDepository_arr(rs.getString("depository_arr"));
                d.setDepository_yp(rs.getString("depository_yp"));
                return d;
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public int updById(DepositoryAndYpBean d) {

        try {
            Connection conn = null;
            PreparedStatement ps = null;//可预处理的语句
            String strsql = "UPDATE depository set " +
                    "depository_name = ?," +
                    "depository_arr= ?," +
                    "depository_readme= ?," +
                    "depository_yp= ?" +
                    " where depository_id= ? ";//可以带参数的语句

            conn = MyConnection.getConnection();

            ps = conn.prepareStatement(strsql);

            ps.setString(1, d.getDepository_name());
            ps.setString(2, d.getDepository_arr());
            ps.setString(3, d.getDepository_readme());
            ps.setString(4, d.getDepository_yp());
            ps.setString(5, d.getDepository_id());

            return ps.executeUpdate();


        } catch (Exception e) {

        }


        return 0;
    }
}
