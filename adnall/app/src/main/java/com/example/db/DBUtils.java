package com.example.db;

import com.example.bean.TalkBean;
import com.example.bean.TsBean;
import com.example.bean.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtils {
    private static String driver = "com.mysql.jdbc.Driver";// MySql驱动

    private static String url = "jdbc:mysql://47.93.50.249:3306/ts?useSSL=false";

    private static String user = "admin";// 用户名

    private static String password = "wangwang01";// 密码

    private static Connection c = null;

    public DBUtils() {

    }

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public HashMap test() {
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = getCon();

        try {
            // mysql简单的查询语句。这里是根据MD_CHARGER表的NAME字段来查询某条记录
            //String sql = "select * from MD_CHARGER where NAME = ?";
            String sql = "select * from user";
            if (connection != null) {// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    // 设置上面的sql语句中的？的值为name
                    // ps.setString(1, name);
                    // 执行sql查询语句并返回结果集
                    ResultSet rs = ps.executeQuery();
                    if (rs != null) {
                        int count = rs.getMetaData().getColumnCount();
                        while (rs.next()) {
                            // 注意：下标是从1开始的
                            for (int i = 1; i <= count; i++) {
                                String field = rs.getMetaData().getColumnName(i);
                                map.put(field, rs.getString(field));
                            }
                        }
                        connection.close();
                        ps.close();
                        return map;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserBean getUser(String username, String pas) {
        UserBean user = new UserBean();
        // 根据数据库名称，建立连接
        Connection connection = getCon();

        try {
            // mysql简单的查询语句。这里是根据MD_CHARGER表的NAME字段来查询某条记录
            //String sql = "select * from MD_CHARGER where NAME = ?";
            String sql = "select * from user where user = ? and password = ?";
            if (connection != null) {// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    // 设置上面的sql语句中的？的值为name
                    ps.setString(1, username);
                    ps.setString(2, pas);
                    // 执行sql查询语句并返回结果集
                    ResultSet rs = ps.executeQuery();
                    if (rs != null) {
                        while (rs.next()) {
                            user.setId(rs.getString("id"));
                            user.setUser(rs.getString("user"));
                            user.setPassword(rs.getString("password"));
                        }
                        connection.close();
                        ps.close();
                        return user;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String insert(String account, String password, String userjjs, String nicknameS, String gender) {
        Connection connection = getCon();
        try {
            // mysql简单的查询语句。这里是根据MD_CHARGER表的NAME字段来查询某条记录
            String sql = "INSERT INTO `user` (`user`, `password`, `nickname`, `sex`, `jj`) VALUES (?, ?, ?, ?,?)";
            if (connection != null) {// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    // 设置上面的sql语句中的？的值为name
                    ps.setString(1, account);
                    ps.setString(2, password);
                    ps.setString(3, nicknameS);
                    ps.setString(4, gender);
                    ps.setString(5, userjjs);
                    // 执行sql查询语句并返回结果集
                    ps.executeUpdate();
                    return "注册成功";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "注册失败";
        }
        return "注册失败";
    }

    public List<TsBean> getTsInfo(String v) {
        try {
            List<TsBean> tsBeanList = new ArrayList<>();
            String sql = "SELECT\n" +
                    "\tpoetry.id, \n" +
                    "\tpoetry.title, \n" +
                    "\tpoetry.author, \n" +
                    "\tpoetry.body, \n" +
                    "\tts_classify.classifyname\n" +
                    "FROM\n" +
                    "\tpoetry\n" +
                    "\tINNER JOIN\n" +
                    "\tts_classify\n" +
                    "\tON \n" +
                    "\t\tpoetry.classify = ts_classify.classify\n";
            Connection connection = getCon();
            PreparedStatement ps;
            if (!v.equals("")) {
                sql = sql + "WHERE\n" + "\tts_classify.classifyname = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, v);
            } else {
                ps = connection.prepareStatement(sql);
            }
            if (ps != null) {
                // 设置上面的sql语句中的？的值
                // 执行sql查询语句并返回结果集
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TsBean tsBean = new TsBean();
                    tsBean.setId(rs.getString("id"));
                    tsBean.setAuthor(rs.getString("author"));
                    tsBean.setClassifyname(rs.getString("classifyname"));
                    tsBean.setTitle(rs.getString("title"));
                    tsBean.setBody(rs.getString("body"));
                    tsBeanList.add(tsBean);
                }
                return tsBeanList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TsBean getTsInfoOne() {
        TsBean tsBean = new TsBean();
        String sql = "SELECT\n" +
                "\tts_classify.classifyname, \n" +
                "\tpoetry.id, \n" +
                "\tpoetry.title, \n" +
                "\tpoetry.author, \n" +
                "\tpoetry.body\n" +
                "FROM\n" +
                "\tts_classify\n" +
                "\tINNER JOIN\n" +
                "\tpoetry\n" +
                "\tON \n" +
                "\t\tts_classify.classify = poetry.classify\n" +
                "\t\tORDER BY RAND() LIMIT 1;";

        try {
            Connection connection = getCon();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tsBean.setId(rs.getString("id"));
                tsBean.setAuthor(rs.getString("author"));
                tsBean.setClassifyname(rs.getString("classifyname"));
                tsBean.setTitle(rs.getString("title"));
                tsBean.setBody(rs.getString("body"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return tsBean;
    }

    public List<TalkBean> getTalkInfo() {
        try {
            List<TalkBean> talkBeanList = new ArrayList<>();
            String sql = "select * from talk";
            Connection connection = getCon();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            if (ps != null) {
                // 设置上面的sql语句中的？的值
                // 执行sql查询语句并返回结果集
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TalkBean talkBean = new TalkBean();
                    talkBean.setId(rs.getString("id"));
                    talkBean.setImageurl(rs.getString("imageurl"));
                    talkBean.setNr(rs.getString("nr"));
                    talkBean.setUsername(rs.getString("username"));
                    talkBeanList.add(talkBean);
                }
                return talkBeanList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String insertTalk(String username, String nr, String imagePath) {
        Connection connection = getCon();
        try {
            // mysql简单的查询语句。这里是根据MD_CHARGER表的NAME字段来查询某条记录
            String sql = "INSERT INTO `talk` (`username`, `nr`, `imageurl`) VALUES (?, ?, ?)";
            if (connection != null) {// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    // 设置上面的sql语句中的？的值为name
                    ps.setString(1, username);
                    ps.setString(2, nr);
                    ps.setString(3, imagePath);
                    // 执行sql查询语句并返回结果集
                    ps.executeUpdate();
                    return "添加成功";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "添加失败";
        }
        return "添加失败";
    }

    public List<TsBean> getTsTitleLikeInfo(String v) {
        try {
            List<TsBean> tsBeanList = new ArrayList<>();
            String sql = "SELECT\n" +
                    "\tpoetry.id, \n" +
                    "\tpoetry.title, \n" +
                    "\tpoetry.author, \n" +
                    "\tpoetry.body, \n" +
                    "\tts_classify.classifyname\n" +
                    "FROM\n" +
                    "\tpoetry\n" +
                    "\tINNER JOIN\n" +
                    "\tts_classify\n" +
                    "\tON \n" +
                    "\t\tpoetry.classify = ts_classify.classify\n" +
                    "WHERE poetry.title like ?";
            Connection connection = getCon();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + v + "%");
            if (ps != null) {
                // 设置上面的sql语句中的？的值
                // 执行sql查询语句并返回结果集
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TsBean tsBean = new TsBean();
                    tsBean.setId(rs.getString("id"));
                    tsBean.setAuthor(rs.getString("author"));
                    tsBean.setClassifyname(rs.getString("classifyname"));
                    tsBean.setTitle(rs.getString("title"));
                    tsBean.setBody(rs.getString("body"));
                    tsBeanList.add(tsBean);
                }
                return tsBeanList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}





