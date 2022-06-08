package servlet;

import model.Product;
import util.StaticUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        String proId = request.getParameter("proId");
        String proName = request.getParameter("proName");
        float proPrice =Float.parseFloat(request.getParameter("proPrice"));
        String proUnit =request.getParameter("proUnit");
        int qty =Integer.parseInt(request.getParameter("qty"));
        String proImg =request.getParameter("proImg");

//为了方便操作购物车中的每个产品，将产品的6项信息封装到一个产品对象中
        Product p = new Product(proId, proName, proPrice, proUnit, qty, proImg);
        //为了模拟购物车，可以使用一个列表来表示购物车
        List<Product> cart = null;
        HttpSession session = request.getSession();
        //如果会话中属性cartList，说明是第一次添加购物车
        if (null == session.getAttribute("carList")){
            cart = new ArrayList<>();
            session.setAttribute("cartList",cart);

        }else {//如果不是第一次添加购物车，从会话获取已有的购物车，继续添加产品
            cart =(List<Product>)session.getAttribute("cartList");
        }
        cart.add(p);
        //购物车列表是放入了会话对象，而不是请求对象，所以不需要转发请求，直接重定向跳转页面即可
        //跳转到cart.jsp页面显示购物车列表
        response.sendRedirect("cart.jsp");*/
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String proId =  request.getParameter("proId");
       String proName =  request.getParameter("proName");
       String proPrice =  request.getParameter("proPrice");
       String proUnit =  request.getParameter("proUnit");
       String proImg =  request.getParameter("proImg");
       String qty =  request.getParameter("qty");


        //为了方便操作购物车中的每个产品，将产品的6项信息封装到一个产品对象中
        Product product = new Product(proId, proName, Float.parseFloat(proPrice), proUnit, Integer.parseInt(qty), proImg);
        //为了模拟购物车，可以使用一个列表来表示购物车
        List<Product> cart;
        HttpSession session = request.getSession();
        //如果会话中属性cartList，说明是第一次添加购物车
        System.out.println("session.getAttribute: " + session.getAttribute("carList"));
     /*   if (null == session.getAttribute("carList")) {
            cart = new ArrayList<>();
            session.setAttribute("cartList",cart);

        }else {//如果不是第一次添加购物车，从会话获取已有的购物车，继续添加产品
            cart =(List<Product>)session.getAttribute("cartList");
        }*/

        if (null == StaticUtil.cart)
            StaticUtil.cart = new ArrayList<>();

        StaticUtil.cart.add(product);
        session.setAttribute("cartList", StaticUtil.cart);

        //购物车列表是放入了会话对象，而不是请求对象，所以不需要转发请求，直接重定向跳转页面即可
        //跳转到cart.jsp页面显示购物车列表
        response.sendRedirect("cart.jsp");

    }
}
