package servlet;

import db.Database;
import model.Usedata;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer.parseInt()
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

       // int id = Integer.parseInt(request.getParameter("id"));
        String name =request.getParameter("name");
        String pwd =request.getParameter("pwd");
        PrintWriter out =response.getWriter();

        try {
            Database database = new Database();
            Usedata usedata =database.login(name,pwd);

            if (usedata==null){
                out.println("该用户不存在或者密码错误"+"<a href=\"http://localhost:8080/web003_war_exploded/\">返回登陆</a>");
            }
            else {
              // out.println("欢迎登陆"+usedata.getName());
                response.sendRedirect("http://localhost:8080/web003_war_exploded/loginsuccess.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
