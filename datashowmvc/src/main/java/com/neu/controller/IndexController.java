package com.neu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName IndexController
 * @Description 首页的controller
 * @Author Dear lin
 * @Date 20:27 2022/6/13
 * @Version 1.0
 **/
@Controller
public class IndexController {
    @RequestMapping("/Index")
    public void getIndex(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //mvc形式接口映射过来
        //对HttpServletRequest中session赋值
        //request.getSession().setAttribute("bookList", shopService.getBookList());
        //转发页面
        response.sendRedirect("index.jsp");

    }
}
