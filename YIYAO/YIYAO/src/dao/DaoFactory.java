package dao;

import dao.LoginDao;
import daoImpl.DepositoryDaoImpl;
import daoImpl.LoginDaoImpl;
import daoImpl.OrderDaoImpl;




public class DaoFactory {
        
	public static LoginDao getLoginDao(){ //登录
    	return new LoginDaoImpl();
    }  
    public static OrderDao getOrderDao(){  
        return new OrderDaoImpl(); 	    	   	
} 
    public static DepositoryDao getDepositoryDao(){
        return new DepositoryDaoImpl();
}
}
