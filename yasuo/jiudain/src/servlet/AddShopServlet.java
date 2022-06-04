package servlet;

import bean.ShoppingBean;
import dao.ShoppingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 1
 */
@WebServlet("/AddShopServlet")
public class AddShopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
//		doGet(request, response);
        //设置编码类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取要添加消费的信息
        ShoppingBean shoppingBean = new ShoppingBean();
        shoppingBean.setDollers(request.getParameter("dollers"));
        shoppingBean.setName(request.getParameter("name"));
        shoppingBean.setType(request.getParameter("type"));
        shoppingBean.setDatetime(request.getParameter("borrowTime"));
        HttpSession session = request.getSession();
        shoppingBean.setAid(String.valueOf(session.getAttribute("aid")));
        ShoppingDao shoppingDao = new ShoppingDao();
        //添加消费信息
        shoppingDao.addshopinfo(shoppingBean);
		//调用函数，存入信息

        response.sendRedirect("admin_jiudian.jsp");
    }

}
