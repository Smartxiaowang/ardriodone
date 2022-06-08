package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "talkServlet", urlPatterns = "/talkServlet")
public class talkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String my_word=request.getParameter("my_word");
        String answer="";
        if (my_word.equals("你好")) {
            answer = "嗨";
        }
        else if (my_word.equals("吃饭了吗？")) {
            answer = "吃了";
        }else {
            answer="我不太明白你的意思";
        }
        if(my_word.equals("1")){
            answer="<a href=\"goods.jsp\">购物</a>";

        }else if (my_word.equals("2")){
            answer="让我们继续聊天吧";
        } else if (my_word.equals("3")){
            answer="<a href=\"index.jsp\">退出系统</a>";
        }else if(my_word.equals("4")){
            answer="<a href=\"loginsuccess.jsp\">购物页面</a>";
        }else if(my_word.equals("卫龙辣条")){
            answer="<a href=\"ProductServlet?pid=1\">卫龙辣条</a>";
        }
        request.setAttribute("Answer",answer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("talk.jsp");
        dispatcher.forward(request,response);


    }
}
