package com.neu.controller;

import com.neu.pojo.BookBean;
import com.neu.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;


    @RequestMapping("/ShowBook")
    public void getIndex(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //mvc形式接口映射过来
        //对HttpServletRequest中session赋值
        request.getSession().setAttribute("bookList", shopService.getBookList());
        //转发页面
        response.sendRedirect("ShowBook.jsp");

    }

    @RequestMapping("/bookinfo")
    public void getBookinfo(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String bookid = request.getParameter("bookid");
        BookBean bookInfo = shopService.getBookInfo(bookid);
        request.getSession().setAttribute("bookInfo", bookInfo);
        response.sendRedirect("bookinfo.jsp");
    }


}
