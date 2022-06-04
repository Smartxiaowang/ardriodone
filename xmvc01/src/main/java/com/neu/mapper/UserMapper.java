package com.neu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import com.neu.pojo.User;


@Mapper
public interface UserMapper {

    /**
     * 根据用户名称，在user表中查找一条记录
     *
     * @param userName 用户名
     * @return 用户的一条记录
     */
    public User findUserByName(String userName);

    /**
     * 检索user表的所有记录
     *
     * @return 所有记录信息
     */
    public List<User> findAll();

    /*
     * @Description: 修改用户信息
     * @Param: [user]
     * @Return: void
     */
    void updUser(User user);

    /*
     * @Description: 添加用户信息
     * @Param: [user]
     * @Return: void
     */
    void insUser(User user);

    /*
     * @Description: 删除用户信息
     * @Param: [user]
     * @Return: void
     */
    void delByUserId(Integer id);
}
