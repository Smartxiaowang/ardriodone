import db.DatabaseUtil;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Database database = new Database();
//        database.showConnect();
        DatabaseUtil databaseUtil = new DatabaseUtil();
        databaseUtil.showConnect();
        //database.insert("校花","123456");
        //database.insert("张三","123456");

//        ArrayList<Usedata>usedata = database.getAlluser();
//       System.out.println("总表里面共有"+usedata.size());
//        for(Usedata each:usedata){
//            System.out.println(each.getId()+"-------"+each.getName()+"-------"+each.getPassword());
//        }
        //Usedata usedata = database.getUser(9);
        //System.out.println(usedata.getId() + usedata.getName());
        //database.close();
//        Usedata usedata = database.login(9, "9");
//        System.out.println(usedata);
        //if (usedata == null) {
            //  System.out.println("该用户不存在或者密码错误");
            //}
            //else {
            //  System.out.println("欢迎登陆"+usedata.getName());
        }
    }
