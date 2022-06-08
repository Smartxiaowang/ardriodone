package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AdminBean;
import bean.JiudianBean;
import bean.HistoryBean;
import dao.AdminDao;
import dao.JiudianDao;
import dao.HistoryDao;
import dao.ReviewDao;

/**
 * Servlet implementation class borrowServlet
 */
@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//设置编码类型
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//为了区分预订和退房的功能,设置tip,tip为1，表示预订
		int tip = Integer.parseInt(request.getParameter("tip"));
		if(tip==1){
			//获取客房id
			int bid = Integer.parseInt(request.getParameter("bid"));
			HttpSession session = request.getSession();
			AdminBean admin = new AdminBean();
			//获取到存入session的aid用户id
			String aid = (String)session.getAttribute("aid");
			AdminDao admindao = new AdminDao();
			JiudianDao jiudianDao = new JiudianDao();
			//通过aid获取到用户的信息
			admin = admindao.get_AidInfo2(aid);
			JiudianBean jiudianBean = jiudianDao.get_JiudianInfo(bid);
			//向review表中插入一条记录
			ReviewDao reviewDao = new ReviewDao();
			//生成当前日期
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			//System.out.println(month);
			int day = c.get(Calendar.DATE);
			//生成预订申请日期
			String begintime = ""+year+"-"+(month+1)+"-"+day;
			reviewDao.addReview(bid,Integer.parseInt(aid),jiudianBean.getCard(),jiudianBean.getName(),
					admin.getUsername(),admin.getName(),begintime);
			//判断管理员审核是否通过,审核通过才将预订记录存入数据表
			//同时将jiudian表对应bid客房的库存数量num-1,客户名borrow_user设为当前的adminbean,预订日期
			//设为当前日期
			/***
			 * 这里有一个bug,就是当用户发起预订时才会执行isPass的判断,而每次预订时其实管理员都是
			 * 待审核状态,因为当预订时而管理员并没有操作的时候,status一定等于0
			 * 所以在执行这个判断的时候isPass永远为false,所以永远不会向预订表中插入记录
			 * 解决办法是在管理员审核通过后在ReviewServlet中直接执行该操作
			 */
//			if(reviewDao.isPass(bid,admin)){//该客房申请审核通过
//				HistoryDao historyDao = new HistoryDao();
//				//获取原先的客房库存数量
//				int jiudianNum = jiudianBean.getcurrentNum();
//				//更新该客房的库存数量,客户名以及预订日期信息
//				jiudianDao.updateJiudianBorrowInfo(bid,jiudianNum-1,admin.getName(),begintime);
//				//向预订记录表中插入一条记录
//				historyDao.borrowJiudian(bid,admin);
//				//将该用户的当前预订数量+1
//				admindao.updateUser(Integer.parseInt(aid),admin.getUsername(),admin.getPassword(),
//						admin.getName(),admin.getEmail(),admin.getPhone(),
//						admin.getLend_num()+1,admin.getMax_num());
//			}
			response.sendRedirect("/jiudain/select.jsp");
		}else{
			//退房功能，获取预订记录的hid
			int hid = Integer.parseInt(request.getParameter("hid"));
			//根据hid来获取bid和aid,并更新jiudian表中的客户名和预订时间以及库存数量
			HistoryDao historyDao = new HistoryDao();
			HistoryBean historyBean = historyDao.getHistoryInfo(hid);
			int bid = historyBean.getBid();
			AdminDao adminDao = new AdminDao();
			HttpSession session = request.getSession();
			//获取到存入session的aid用户id
			String aid = (String)session.getAttribute("aid");
			AdminBean admin = adminDao.get_AidInfo2(aid);
			JiudianDao jiudianDao = new JiudianDao();
			JiudianBean jiudianBean = jiudianDao.get_JiudianInfo(bid);
			//退房后,则该客房客户名和预订时间都为"无",同时库存数量+1
			jiudianDao.updateJiudianBorrowInfo(bid,jiudianBean.getcurrentNum()+1,
					"无","无");
			//调用退房功能,改变预订记录表history中status的状态(变为2)
			historyDao.borrowJiudian2(hid);
			//将该用户的当前预订数量-1
			adminDao.updateUser(Integer.parseInt(aid),admin.getUsername(),admin.getPassword(),
					admin.getName(),admin.getEmail(),admin.getPhone(),
					admin.getLend_num()-1,admin.getMax_num());
			response.sendRedirect("/jiudain/borrow.jsp");
//			/**
//			 * 退房在管理员和用户界面都有，为了区分，设置了show字段，show为1表示用户界面
//			 */
//			int show = Integer.parseInt(request.getParameter("show"));
//			//调用退房函数，改变status字段
//			jiudiandao.borrowJiudian2(hid);
//			if(show==1){
//				response.sendRedirect("/jiudain/borrow.jsp");
//			}else{
//				response.sendRedirect("/jiudain/admin_borrow.jsp");
//			}
			
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
