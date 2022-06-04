package com.neu.service;

import java.util.List;

import com.neu.pojo.User;

/**
 * 用户管理逻辑类
 * @author Admin
 *
 */
public interface UserService {
	
	/**
	 * 根据用户名，获得一个用户
	 * @param userName 用户名
	 * @return 一个用户
	 */
	public User getUser(String userName);
	
	/**
	 * 根据用户名和密码判断用户是否登录
	 * @param userName 用户名
	 * @param password 密码
	 * @return true或false
	 */
	public boolean isLogin(User user);
	
	/**
	 * 所有用户信息
	 * @return 所有用户
	 */
	public List<User> getUsers();

    void updUser(User user);

	void insertUser(User user);

	void delByUserId(Integer id);
}
