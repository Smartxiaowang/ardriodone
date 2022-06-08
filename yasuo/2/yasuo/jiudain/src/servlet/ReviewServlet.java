package servlet;

import bean.AdminBean;
import bean.JiudianBean;
import bean.ReviewBean;
import dao.AdminDao;
import dao.JiudianDao;
import dao.HistoryDao;
import dao.ReviewDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        //doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理用户预订申请,同意或者不同意
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取申请记录id
        int rid = Integer.parseInt(request.getParameter("rid"));
        //获取管理员操作(审核)结果
        int status = Integer.parseInt(request.getParameter("status"));
        ReviewDao reviewDao = new ReviewDao();
        //更新该申请的审核状态
        reviewDao.updateReview(rid,status);
        /***
         * 同时根据审核的结果,如果通过的话将预订记录存入数据表
         * 同时将jiudian表对应bid客房的库存数量num-1,客户名borrow_user设为当前的adminbean,预订日期
         * 设为当前日期
         */
        if(reviewDao.isPass(rid)){  //审核通过才做这些操作
            ReviewBean bean = reviewDao.getReviewBean(rid);
            AdminDao adminDao = new AdminDao();
            JiudianDao jiudianDao = new JiudianDao();
            HistoryDao historyDao = new HistoryDao();
            int aid = bean.getAid();        //获取申请者id
            int bid = bean.getBid();        //获取申请客房的id
            AdminBean admin = adminDao.get_AidInfo(aid);
            JiudianBean jiudianBean = jiudianDao.get_JiudianInfo(bid);
            //获取原先的客房库存数量
            int jiudianNum = jiudianBean.getcurrentNum();
            //生成当前日期
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DATE);
            //生成预订日期
            String begintime = ""+year+"-"+(month+1)+"-"+day;
            //更新该客房的库存数量,客户名以及预订日期信息
            jiudianDao.updateJiudianBorrowInfo(bid,jiudianNum-1,admin.getName(),begintime);
            //向预订记录表中插入一条记录
            historyDao.borrowJiudian(bid,admin);
            adminDao.updateUser(aid,admin.getUsername(),admin.getPassword(),
						admin.getName(),admin.getEmail(),admin.getPhone(),
						admin.getLend_num()+1,admin.getMax_num());
        }
        response.sendRedirect("/jiudain/admin_review.jsp");
    }
}
