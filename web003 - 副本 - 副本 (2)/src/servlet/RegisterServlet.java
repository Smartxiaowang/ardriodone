package servlet;

import db.Database;
import model.Usedata;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/*@WebServlet(name = "RegisterServlet", urlPatterns = "/R")*/
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String name =request.getParameter("name");
        String pwd =request.getParameter("pwd");
        String pwd2 =request.getParameter("pwd2");
        PrintWriter out =response.getWriter();
        if(pwd.equals(pwd2)){
            try {
                Database database = new Database();
                database.insert(name,pwd);
                out.println("注册成功");
                ArrayList<Usedata>usedata = database.getAlluser();
                out.println("总表里面共有"+usedata.size()+"组数据"+"<br>");
                 for(Usedata each:usedata) {
                   out.println(each.getId() + "-------" + each.getName() + "-------" + each.getPassword()+"<br>");
                 }
                out.println("请返回登录界面"+"<a href=\"http://localhost:8080/web003_war_exploded/\">返回登陆</a>");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                out.println("注册失败");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("注册失败2");
            }
        }
        else {
            out.println("两次输入的密码不一致");
            out.println("请返回重新注册"+"<a href=\"http://localhost:8080//Register.jsp\">返回注册</a>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
