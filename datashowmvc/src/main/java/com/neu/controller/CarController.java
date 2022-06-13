package com.neu.controller;

import com.neu.pojo.BookBean;
import com.neu.pojo.CarBean;
import com.neu.service.CarService;
import com.neu.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private ShopService shopService;


    @RequestMapping("/ShopCar")
    public void getBookinfo(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String bookid = request.getParameter("bookid");
        //获取路径上的参数
        request.getSession().setAttribute("msg", null);
        //先把session中用于提示信息的msg清除
        if (bookid != null) {
            //说明是查询单挑数据的信息
            BookBean bookInfo = shopService.getBookInfo(bookid);
            //获取改图书的详细信息
            int i = carService.insertCar(bookInfo);
            //把这条信息加入到数据库中
            if (i != 0) {
                //往session中写入数据
                request.getSession().setAttribute("carList", carService.getCarBookList());
                //往session中写入数据返回页面
               response.sendRedirect("shoopcar.jsp");

            } else {
                //插入数据失败
                request.setAttribute("msg", "添加失败");
                response.sendRedirect("ShowBook.jsp");
            }
        } else {
            //查询购物车的所有数据是否为空
            List<CarBean> carList = carService.getCarBookList();
            if (carList.size() != 0) {
                response.sendRedirect("shoopcar.jsp");
            }else {
                request.getSession().setAttribute("msg", "购物车为空");
                response.sendRedirect("ShowBook.jsp");
            }
        }
    }

}
