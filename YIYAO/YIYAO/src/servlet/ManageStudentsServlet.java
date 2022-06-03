package servlet;

import bean.DepositoryAndYpBean;
import bean.OrderBean;
import dao.DaoFactory;
import dao.DepositoryDao;
import dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ManageStudentsServlet extends HttpServlet implements Serializable {


    private DepositoryDao dao = DaoFactory.getDepositoryDao();


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtSearchNumber = "";
        if (request.getParameter("txtSearchNumber")!= null && !request.getParameter("txtSearchNumber").equals(""))
            txtSearchNumber = request.getParameter("txtSearchNumber");
        //把查询条件带回給页面
        request.setAttribute("searchNumber", txtSearchNumber);
        List<DepositoryAndYpBean> dylist = dao.select(txtSearchNumber);//紧耦合变成松耦合
        request.setAttribute("dylist", dylist);


        //列表当中显示班级 解决外键问题
        request.getRequestDispatcher("cangku.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hidOperationType = request.getParameter("hidOperationType");
        if (hidOperationType != null && hidOperationType.compareTo("add") == 0) {
            //添加
            String depository_name = request.getParameter("depository_name");
            String depository_arr = request.getParameter("depository_arr");
            String depository_readme = request.getParameter("depository_readme");
            String pid = request.getParameter("pid");
            DepositoryAndYpBean d = new DepositoryAndYpBean();
            d.setDepository_arr(depository_arr);
            d.setDepository_name(depository_name);
            d.setDepository_yp(pid);
            d.setDepository_readme(depository_readme);
            if (dao.insert(d) > 0) {
                request.setAttribute("message", "添加成功！！！");
            } else {
                request.setAttribute("message", "添加失败！！！");
            }
        }
        if (hidOperationType != null && hidOperationType.compareTo("remove") == 0) {
            int uId = Integer.valueOf(request.getParameter("hidUId"));

            if (dao.delete(uId) > 0) {
                request.setAttribute("message", "删除成功！！！");
            } else {
                request.setAttribute("message", "删除失败！！！");
            }
        }
        if (hidOperationType != null && hidOperationType.compareTo("modfiy") == 0) {
            int uId = Integer.valueOf(request.getParameter("hidUId"));

            DepositoryAndYpBean d = dao.selectById(uId);

            if (d != null) {
                request.setAttribute("modfiyOrder", d);
            } else {
                request.setAttribute("message", "");
            }
        }
        if (hidOperationType != null && hidOperationType.compareTo("save") == 0) {
            String depository_name = request.getParameter("depository_name");
            String depository_arr = request.getParameter("depository_arr");
            String depository_readme = request.getParameter("depository_readme");
            String pid = request.getParameter("pid");
            DepositoryAndYpBean d = new DepositoryAndYpBean();
            d.setDepository_arr(depository_arr);
            d.setDepository_id(request.getParameter("hidUId"));
            d.setDepository_name(depository_name);
            d.setDepository_yp(pid);
            d.setDepository_readme(depository_readme);
            if (dao.updById(d) > 0) {
                request.setAttribute("message", "修改成功！！！");
            } else {
                request.setAttribute("message", "修改失败！！！");
            }

        }
       /* if (hidOperationType != null && hidOperationType.compareTo("search") == 0) {
            String txtSearchNumber = request.getParameter("txtSearchNumber");
            List<DepositoryAndYpBean> dylist = dao.select(txtSearchNumber);
            request.setAttribute("dylist", dylist);
        }*/


        doGet(request, response);
    }


}