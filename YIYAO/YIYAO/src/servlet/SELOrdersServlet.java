package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
import dao.DaoFactory;
import dao.OrderDao;

public class SELOrdersServlet extends HttpServlet implements Serializable {

    private OrderDao dao = DaoFactory.getOrderDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String txtSearchNumber = "";
        if (request.getParameter("txtSearchNumber") != null)
            txtSearchNumber = request.getParameter("txtSearchNumber");
        //把查询条件带回給页面
        request.setAttribute("searchNumber", txtSearchNumber);
        List<OrderBean> orders = dao.select(txtSearchNumber);//紧耦合变成松耦合
        request.setAttribute("orders", orders);
        //列表当中显示班级 解决外键问题
        request.getRequestDispatcher("SELOrders.jsp").forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hidOperationType = request.getParameter("hidOperationType");
        if (hidOperationType != null && hidOperationType.compareTo("cancelSave") == 0) {

        }
        doGet(request, response);


    }


}
