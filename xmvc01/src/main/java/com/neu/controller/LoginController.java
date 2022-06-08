package com.neu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.pojo.User;
import com.neu.service.UserService;

import java.io.IOException;

/**
 * 登录功能Controller
 *
 * @author Admin
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String getIndex() {
        return "login";
    }

    /**
     * 首先打开网页之后输入用户名密码点击提交之后后来到这个方法处
     *
     * @param user    用户信息
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public void getLogin(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //代码编写处
        boolean login = userService.isLogin(user);
        if (login)
            //登录成功需要跳转到users界面
            response.sendRedirect("users");
        else
            response.sendRedirect("index");

    }

}
