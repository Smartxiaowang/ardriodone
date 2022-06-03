package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.OrderDao;
import bean.OrderBean;

public class OrdersServlet extends HttpServlet implements Serializable {


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
        request.getRequestDispatcher("Orders.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hidOperationType = request.getParameter("hidOperationType");

        if (hidOperationType != null && hidOperationType.compareTo("add") == 0) {
            String txtcName = request.getParameter("txtName");
            String txtmoney = request.getParameter("txtNumber");
            String txtbeneficiary = request.getParameter("txtType");
            String txtName = request.getParameter("txtDescription");

            OrderBean orders = new OrderBean();
            orders.setName(txtcName);
            orders.setNumber(txtmoney);
            orders.setType(txtbeneficiary);
            orders.setDescription(txtName);

            if (dao.insert(orders) > 0) {
                request.setAttribute("message", "添加成功！！！");
            } else {
                request.setAttribute("message", "添加失败！！！");
            }
        } else if (hidOperationType != null && hidOperationType.compareTo("remove") == 0) {
            int uId = Integer.valueOf(request.getParameter("hidUId"));

            if (dao.delete(uId) > 0) {
                request.setAttribute("message", "删除成功！！！");
            } else {
                request.setAttribute("message", "删除失败！！！");
            }
        } else if (hidOperationType != null && hidOperationType.compareTo("modfiy") == 0) {
            int uId = Integer.valueOf(request.getParameter("hidUId"));

            OrderBean orders = dao.select(uId);

            if (orders != null) {
                request.setAttribute("modfiyOrder", orders);
            } else {
                request.setAttribute("message", "没有找到订单信息，不能修改！！");
            }
        } else if (hidOperationType != null && hidOperationType.compareTo("cancelSave") == 0) {

        } else if (hidOperationType != null && hidOperationType.compareTo("save") == 0) {
            int uId = Integer.valueOf(request.getParameter("hidUId"));

            String txtcName = request.getParameter("txtName");
            String txtmoney = request.getParameter("txtNumber");
            String txtbeneficiary = request.getParameter("txtType");
            String txtName = request.getParameter("txtDescription");

            OrderBean orders = new OrderBean();
            orders.setuId(uId);
            orders.setName(txtcName);
            orders.setNumber(txtmoney);
            orders.setType(txtbeneficiary);
            orders.setDescription(txtName);
            if (dao.update(orders) > 0) {
                request.setAttribute("message", "修改成功！！！");
            } else {
                request.setAttribute("message", "修改失败！！！");
            }
        }
        doGet(request, response);
    }


}