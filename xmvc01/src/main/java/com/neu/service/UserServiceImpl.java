package com.neu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.mapper.UserMapper;
import com.neu.pojo.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(String userName) {
        return userMapper.findUserByName(userName);
    }

    /**
     * 登录验证方法的实现
     */
    public boolean isLogin(User user) {

        //代码编写处
        //判定用户名密码不为空 或 空字符串 为空返回假
        if (user.getUserName() != null && !user.getUserName().equals("")) {
            //根据用户名查询具体信息
            User readyLoginUser = getUser(user.getUserName());
            //比对密码是否相同 相同返回真
            if (user.getPassword().equals(readyLoginUser.getPassword())) {
                return true;
            }
        }

        //如果不同最后即返回假
        return false; //请自行修改代码
    }

    public List<User> getUsers() {

        return userMapper.findAll();
    }

    /*
     * @Description: 修改数据
     * @Param: [user]
     * @Return: void
     */
    @Override
    public void updUser(User user) {
        userMapper.updUser(user);
    }
    /*
     * @Description: 新增数据
     * @Param: [user]
     * @Return: void
     */
    @Override
    public void insertUser(User user) {
        userMapper.insUser(user);
    }

    /*
     * @Description: 删除数据
     * @Param: [user]
     * @Return: void
     */
    @Override
    public void delByUserId(Integer id) {
        userMapper.delByUserId(id);
    }

}
