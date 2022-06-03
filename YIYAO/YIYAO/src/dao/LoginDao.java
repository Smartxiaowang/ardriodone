package dao;

import java.util.List;

import bean.LoginBean;



public interface LoginDao {
	
    public  int insert(LoginBean login);
    public  int delete(LoginBean login);
    public  int delete(int loginId);
    public  int update(LoginBean login);
    public  List<LoginBean> select();
    public  LoginBean select(String loginName);
    public  LoginBean select(int loginId);
}
