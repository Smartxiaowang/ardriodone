package servlet;

import dao.ProductDao;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao= new ProductDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("1");
        //1. 获取请求参数
        String pid = request.getParameter("pid");
        //2. 处理请求
        //根据产品编号查询产品的详细信息，返回的是一个Product对象，包含这个产品的详细信息
        System.out.println(pid);
        Product p =productDao.findProductById(pid);

        //3. 生成动态响应
        //Servlet和JSP的最佳使用方式：请求交给Servlet处理，产生的数据交给JSP页面显示
        //如果一个数据需要在整个会话期间使用，可以将数据作为属性添加到会话对象中
        //如果一个数据不需要在整个会话期间使用，只想传递给下一个组件(servlet或jsp)，可以将数据作为属性添加到请求对象传递给下一个页面
        //然后转发请求
        request.setAttribute("product",p);

        //转发请求需要使用请求派发器
        RequestDispatcher rd = request.getRequestDispatcher("/productInfo.jsp");
        rd.forward(request,response);
//        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");
/*        List<Product> product = new ArrayList<>();
        product.add(new Product("1","卫龙辣条",23,"包",2500,"P001.jpg"));
        product.add(new Product("2","青岛大虾",98,"包",500,"P002.jpg"));
        product.add(new Product("3","四级真题",28,"套",3500,"P003.jpg"));
        product.add(new Product("4","快乐肥仔水",23,"瓶",300,"P004.jpg"));*/

        System.out.println("通过商品id查找对应的商品信息");
        //1. 获取请求参数
        String pid = request.getParameter("pid");
        //2. 处理请求
        //根据产品编号查询产品的详细信息，返回的是一个Product对象，包含这个产品的详细信息
        System.out.println(pid);
        Product p =productDao.findProductById(pid);

        //3. 生成动态响应
        //Servlet和JSP的最佳使用方式：请求交给Servlet处理，产生的数据交给JSP页面显示
        //如果一个数据需要在整个会话期间使用，可以将数据作为属性添加到会话对象中
        //如果一个数据不需要在整个会话期间使用，只想传递给下一个组件(servlet或jsp)，可以将数据作为属性添加到请求对象传递给下一个页面
        //然后转发请求
        request.setAttribute("product",p);
        request.getRequestDispatcher("productInfo.jsp").forward(request,response);
    }
}
