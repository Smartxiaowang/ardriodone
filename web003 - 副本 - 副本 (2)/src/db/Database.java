package db;

import model.Usedata;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection conn = null;
    public  Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//注册驱动
        this.conn = DriverManager.getConnection("jdbc:mysql://47.93.50.249:3306/web003?serverTimezone=GMT%2B8","admin","wangwang01");
        System.out.println(conn);
    }
    public  void  showConnect(){
        if (conn==null){
            System.out.println("数据库连接失败");
        }else {
            System.out.println("数据库连接成功");
        }

    }
    //关闭链接
    public  void close() throws SQLException {
        conn.close();
    }
    //在Login表里面添加数据
    public void insert(String name,String pwd)throws  SQLException{
        PreparedStatement prep = conn.prepareStatement("insert into web003.login value (null ,?,?)");
        prep.setString(1,name);
        prep.setString(2,pwd);
        prep.execute();
    }

    public ArrayList<Usedata>getAlluser() throws SQLException {
        ArrayList<Usedata> uselist =new ArrayList<Usedata>();
        PreparedStatement prep =conn.prepareStatement("select * from  web003.login");
       prep.execute();
       ResultSet resultSet =prep.executeQuery();
       while (resultSet.next()){
           int myid =resultSet.getInt("id");
           String myname = resultSet.getString("name");
           String mypwd = resultSet.getString("password");
           uselist.add(new Usedata(myid,myname,mypwd));
       }
        return uselist;

    }
    //在表里查询数据
    public  Usedata getUser(String name) throws SQLException {
        PreparedStatement prep =conn.prepareStatement("select * from  web003.login where name=?");
        prep.setString(1,name);
        prep.execute();
        ResultSet resultSet = prep.executeQuery();
        if(resultSet.next()){
            int myid =resultSet.getInt("id");
            String myname = resultSet.getString("name");
            String mypwd = resultSet.getString("password");
            return new Usedata(myid,myname,mypwd);
        }
        return null;
    }
    public Usedata login(String name,String pwd) throws SQLException {
        PreparedStatement prep =conn.prepareStatement("select   web003.login.password from web003.login where name =?");
        prep.setString(1,name);
        prep.execute();
        ResultSet resultSet = prep.executeQuery();
        if(resultSet.next()){
            String mypwd = resultSet.getString("password");
            //从数据库查询判断是否相等
            if (pwd.equals(mypwd)){
                return getUser(name);

            }else
                return  null;
        }else
            return null;
    }
}
