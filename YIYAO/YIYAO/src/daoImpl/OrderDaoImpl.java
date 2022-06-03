package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MyConnection;
import dao.OrderDao;
import bean.OrderBean;

public class OrderDaoImpl implements OrderDao {

	@Override
	public int insert(OrderBean orders) {
		   Connection conn=null;
	   PreparedStatement ps=null;//可预处理的语句
	   String strsql="insert into orders values(  ?, ?,?, ?)";//可以带参数的语句   
	   try {
		conn=MyConnection.getConnection();

		ps.setString(1,orders.getName());
		ps.setString(2,orders.getType());
		ps.setString(3,orders.getNumber());
		ps.setString(4,orders.getDescription());

		ps=conn.prepareStatement(strsql);
		return ps.executeUpdate();

	} catch (SQLException e) {	
		e.printStackTrace();
		System.out.println("添加药品信息异常");
		
	}finally{
		MyConnection.close(ps,conn);
		  
	}
	   	  
		  return 0;	   
	}

	@Override
	public int delete(int uId) {
		Connection conn=null;
		   PreparedStatement ps=null;//可预处理的语句
		   String strsql="delete from orders where UId=? ";//可以带参数的语句   	   
		   try {
			conn=MyConnection.getConnection();
					
			ps=conn.prepareStatement(strsql);
			ps.setInt(1,uId);
			
			return ps.executeUpdate();

		} catch (SQLException e) {	
			e.printStackTrace();
			System.out.println("删除药品信息异常");
			
		}finally{
			MyConnection.close(ps,conn);
			  
		}
	   
	   return 0;
	}

	@Override
	public int update(OrderBean orders) {
		  Connection conn=null;
		   PreparedStatement ps=null;//可预处理的语句
		   String strsql=" update orders set Name=?,"
		   		+ " Type=?,Number=? ,Description=? "
		   		+ " where UId=? ";//可以带参数的语句	   		   
		   try {
			conn=MyConnection.getConnection();					
			ps=conn.prepareStatement(strsql);
			int count=1;
			ps.setString(count++, orders.getName());
			ps.setString(count++,orders.getType());
			ps.setString(count++,orders.getNumber());
			ps.setString(count++,orders.getDescription());	
			ps.setInt(count++,orders.getuId());	
			return ps.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
			System.out.println("修改药品信息异常");			
		}finally{
			MyConnection.close(ps,conn);			  
		}
		   return 0;	   
	}

	@Override
	public List<OrderBean> select() {
		 Connection conn=null;

		   PreparedStatement ps=null;//可预处理的语句
		   ResultSet rs=null;
		   String strsql=" select * from orders ";//可以带参数的语句
		   List<OrderBean> orders=new ArrayList<OrderBean>();
		   
		   
		   try {
			conn=MyConnection.getConnection();
					
			ps=conn.prepareStatement(strsql);
			
			rs=ps.executeQuery();
			
			while(rs.next()){		
				orders.add(getOrder(rs));
				
			}
		} catch (SQLException e) {	
			e.printStackTrace();
			System.out.println("查询药品信息异常");
			
		}finally{
			MyConnection.close(rs,ps,conn);
			  
		}
		   return orders;	
	}

	@Override
	public List<OrderBean> select(String searchNumber) {
		Connection conn=null;
		   PreparedStatement ps=null;//可预处理的语句
		   ResultSet rs=null;
		   String strsql=" select * from orders"
		   		+ " where  Name like ?";//可以带参数的语句
		   
		   
	 List<OrderBean> orders=new ArrayList<>();
		   
		   
		   try {
			conn=MyConnection.getConnection();
					
			ps=conn.prepareStatement(strsql);
			ps.setString(1, "%" + searchNumber + "%");
			rs=ps.executeQuery();
			
			while(rs.next()){						
				orders.add(getOrder(rs));
				
			}
		} catch (SQLException e) {	
			e.printStackTrace();
			System.out.println("查询药品信息异常");
			
		}finally{
			MyConnection.close(rs,ps,conn);
			  
		}
		return orders;
	}

	@Override
	public OrderBean select(int uId) {
		Connection conn=null;

		   PreparedStatement ps=null;//可预处理的语句
		   ResultSet rs=null;
		   String strsql=" select * from orders where UId = ?";//可以带参数的语句
		   
		   
		   try {
			conn=MyConnection.getConnection();
					
			ps=conn.prepareStatement(strsql);
			ps.setInt(1, uId);
			rs=ps.executeQuery();
			
			while(rs.next()){						
				return getOrder(rs);
				
			}
		} catch (SQLException e) {	
			e.printStackTrace();
			System.out.println("获取药品信息异常");
			
		}finally{
			MyConnection.close(rs,ps,conn);
			  
		}
		return null;
	}

	private OrderBean getOrder(ResultSet rs) throws SQLException {
		OrderBean order=new OrderBean();
		int count=1;
		order.setuId(rs.getInt(count++));
		order.setName(rs.getString(count++));
		order.setType(rs.getString(count++));
		order.setNumber(rs.getString(count++));
		order.setDescription(rs.getString(count++));
		return order;
	}
}
