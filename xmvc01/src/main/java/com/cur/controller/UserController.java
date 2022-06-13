package com.cur.controller;

import com.cur.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cur.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户管理Controller
 *
 * @author Admin
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public ModelAndView  getIndex() {
        ModelAndView modelAndView = new ModelAndView();
        //查询所有的用户数据根据前端users.jsp的28行规定的名字进行赋值
        modelAndView.addObject("userList", userService.getUsers());
        //数据返回的jsp的名字
        modelAndView.setViewName("users");
        //返回modelAndView
        return modelAndView; //请修改代码
    }
    @RequestMapping("/insert")
    public ModelAndView  insert() {
        ModelAndView modelAndView = new ModelAndView();
        //数据返回的jsp的名字
        modelAndView.setViewName("insert");
        //返回modelAndView
        return modelAndView; //请修改代码
    }
    @RequestMapping("/upduser")
    public ModelAndView  upduser() {
        ModelAndView modelAndView = new ModelAndView();
        //数据返回的jsp的名字
        modelAndView.setViewName("upduser");
        //返回modelAndView
        return modelAndView; //请修改代码
    }
    @RequestMapping("/deluser")
    public ModelAndView  deluser() {
        ModelAndView modelAndView = new ModelAndView();
        //数据返回的jsp的名字
        modelAndView.setViewName("deluser");
        //返回modelAndView
        return modelAndView; //请修改代码
    }
    //--------------以上是固定写法用于打开页面---------------------------------------------//

    //--------------以下是删改查逻辑---------------------------------------------//
    /*
     * @Description: 修改信息
     * @Param: [user, response]
     * @Return: void
     */
    @RequestMapping("/upd")
    public void updUserInfo(User user,HttpServletResponse response) throws IOException {
        //修改用户信息
        userService.updUser(user);
        //跳转到users页面的方法
        response.sendRedirect("users");;
    }

    @RequestMapping("/insertUser")
    public void insertUser(User user, HttpServletResponse response) throws IOException {
        //代码编写处
        //插入新的user信息
        userService.insertUser(user);
        //跳转到users页面的方法
        response.sendRedirect("users");
    }

    @RequestMapping("/delUser")
    public void delUser(Integer id,HttpServletResponse response) throws IOException {
        //根据id删除
        userService.delByUserId(id);
        //跳转到users页面的方法
        response.sendRedirect("users");;
    }
}
