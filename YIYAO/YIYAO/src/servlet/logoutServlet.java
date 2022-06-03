package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();		
		session.removeAttribute("LoginName");
		session.invalidate();//使原会话失效
		
		//移除cookie使自动登录失效
		Cookie cookie=new Cookie("aoutoLogin","");
		cookie.setMaxAge(1);//设置cookie的有效时间，以秒为单位
	    response.addCookie(cookie);
		
		response.sendRedirect("LoginServlet");//重回登录页面
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		    doGet(request,response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}