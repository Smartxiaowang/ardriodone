package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.LoginDao;
import bean.LoginBean;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// System.out.println(cookie.getName()+":"+cookie.getValue());
				if (cookie.getName().compareTo("LoginName") == 0) {
					request.setAttribute("LoginName", cookie.getValue());
					break;
				}
				if (cookie.getName().compareTo("aoutoLogin") == 0) {
					// 找到了就自动登录
					HttpSession session = request.getSession();
					session.setAttribute("LoginName", cookie.getValue());
					response.sendRedirect("indexServlet");
					return; // 避免二次响应
				}
			}
		}

		request.getRequestDispatcher("Login.jsp").forward(request, response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String LoginName = request.getParameter("txtLoginName");
		String pwdName = request.getParameter("txtpwdName");

		LoginBean login=isLogin(LoginName,pwdName);	
		
		
		if (login!=null) {
			// request.setAttribute("LoginName",LoginName );
			HttpSession session = request.getSession();
			session.setAttribute("LoginName", LoginName);// 使用会话对象可以保存登录相关信息
			

			// 设置Cookie 记住账号
			// 1.创建Cookie对象，并设置键值对 ，Cookie的值仅能是字符串类型
			// 2.需要使用响应对象将Cookie带到浏览器端
			// 3.默认情况下Cookie的有效期与浏览器一致，可以使用setMaxAge()设置有效期
			// 用户名正确，并且选择了记住登录名 ，将登录名信息保存到cookie中
			if (request.getParameter("rememberLoginName") != null) {
				Cookie cookie = new Cookie("LoginName", LoginName);
				cookie.setMaxAge(7 * 24 * 60 * 60);// 设置Cookie的有效期，以秒为单位
				response.addCookie(cookie);// 从服务端到浏览器端保存cookie对象，需要使用响应对象完成

			} else {
				// cookie信息移除
				// 1.移除Cookie没有相关的remove方法
				// 2.创建一个同名Cookie，并将值设置为空字符串
				// 3.或者将同名Cookie的有效期设置极短
				Cookie cookie = new Cookie("LoginName", "");
				cookie.setMaxAge(1);// 设置cookie的有效时间，以秒为单位
				response.addCookie(cookie);
			}
			// 自动登录cookie设置
			if (request.getParameter("aoutoLogin") != null) {
				Cookie cookie = new Cookie("aoutoLogin", LoginName);
				cookie.setMaxAge(7 * 24 * 60 * 60);// 设置Cookie的有效期，以秒为单位
				response.addCookie(cookie);// 从服务端到浏览器端保存cookie对象，需要使用响应对象完成
			}
			//response.sendRedirect("indexServlet");// 响应重定向
			if(login.getLoginType().compareTo("管理员")==0){
				response.sendRedirect("OrdersServlet");
			} else if(login.getLoginType().compareTo("用户")==0){
				response.sendRedirect("SELOrdersServlet");
			}  
			
			
		} else {
			request.setAttribute("errorMessage", "  用户名或者密码错误！");
			// response.sendRedirect("Login.jsp");//响应重定向时 ，原请求和响应对象失效

			// 请求转发<jsp:forword>,原请求和响应对依然有效
			request.getRequestDispatcher("Login.jsp")
					.forward(request, response);
		}
		
	}


		//验证登录方法
	public LoginBean isLogin(String loginName,String password){
		
		LoginDao dao=DaoFactory.getLoginDao();
		
		LoginBean login=dao.select(loginName);
		//数据库中有该登录信息且登陆密码 与输入密码 相同则是有效登录 
		if(login != null && login.getPassword().compareTo(password)==0)
			return login;
		else
			return null;
	}




}
