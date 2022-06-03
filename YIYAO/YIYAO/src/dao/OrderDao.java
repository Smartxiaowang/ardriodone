package dao;

import java.util.List;

import bean.OrderBean;


public interface OrderDao {
	 public  int insert(OrderBean orders); 
	 public  int delete(int uId);
	 public  int update(OrderBean orders);
	 public List<OrderBean>select();
	 public List<OrderBean>select(String searchNumber);
	 public OrderBean select(int uId);
}
