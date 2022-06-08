package com.edu.servlet;

import com.edu.entity.CommandDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class addCommand
 */
@WebServlet("/addCommand")
public class addCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCommand() {
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

//		只能删除当前电影的评论
		int mId =Integer.parseInt(request.getParameter("mid"));
//		只能自己的评论
		int uId= Integer.parseInt(request.getParameter("uid"));
		
//		获取当前时间
		Date curTime=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cTime=dateFormat.format(curTime);
		String cWord = request.getParameter("content");
//		连接数据库，插入一条评论
		CommandDao dao=new CommandDao();
		//dao.getConnection(server, dbname, user, pwd);
		if(dao.add(uId, mId, cWord, cTime)){
			response.sendRedirect("Command.jsp?id="+mId);
		}else{
			response.sendRedirect("Command.jsp?id="+mId);
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
