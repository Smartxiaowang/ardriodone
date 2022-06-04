package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.CommandDao;

/**
 * Servlet implementation class deleteCommand
 */
public class deleteCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletContext ctx=request.getServletContext();
		String server=ctx.getInitParameter("server");
		String dbname=ctx.getInitParameter("db");
		String user=ctx.getInitParameter("user");
		String pwd=ctx.getInitParameter("pwd");
		String cId=request.getParameter("cId");
//		连接数据库，删除记录
		CommandDao dao =new CommandDao();
		dao.getConnection(server, dbname, user, pwd);
		if(dao.delete(cId)){
			response.sendRedirect("Command.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
