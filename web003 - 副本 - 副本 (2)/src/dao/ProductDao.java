package dao;

import db.DatabaseUtil;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> getProductList() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList=new ArrayList();

        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement("select * from web003.product");
            rs = ps.executeQuery();

            while (rs.next()) {
                productList.add(new Product(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            DatabaseUtil.close(rs,ps,con);
        }
        return null;
    }

    /**
     * 根据产品编号查询产品信息
     * @param productId 字符串表示的产品编号
     * @return 包含产品所有信息的一个Product对象，如果没有查询到该产品，则返回null
     */
    public Product findProductById(String productId) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement("select * from web003.product where productId=?");
            ps.setString(1, productId);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println("商品信息: " + resultSet.getString(1) + resultSet.getString(2) + resultSet.getFloat(3) + resultSet.getString(4) + resultSet.getInt(5) + resultSet.getString(6));
                return new Product(resultSet.getString(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            DatabaseUtil.close(resultSet,ps,con);
        }
        return null;
    }
}
