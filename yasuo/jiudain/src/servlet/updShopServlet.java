package servlet;

import bean.ShoppingBean;
import dao.ShoppingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1
 */
@WebServlet("/updShopServlet")
public class updShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public updShopServlet() {
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
		//修改客房信息
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String bid = request.getParameter("bid");
		String name = request.getParameter("name");
		String dollers = request.getParameter("dollers");
		String datetimes = request.getParameter("datetime");
		String type = request.getParameter("type");
		ShoppingBean shoppingBean = new ShoppingBean();
		shoppingBean.setBid(bid);
		shoppingBean.setName(name);
		shoppingBean.setDollers(dollers);
		shoppingBean.setType(type);
		shoppingBean.setDatetime(datetimes);
		new ShoppingDao().updateShoopInfo(shoppingBean);
		response.sendRedirect("admin_jiudian.jsp");
	}

}
