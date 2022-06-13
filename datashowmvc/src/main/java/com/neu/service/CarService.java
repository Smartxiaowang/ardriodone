package com.neu.service;

import com.neu.pojo.BookBean;
import com.neu.pojo.CarBean;

import java.util.List;

/**
 * 用户管理逻辑类
 * @author Admin
 *
 */
public interface CarService {


	int insertCar(BookBean bookInfo);

	List<CarBean> getCarBookList();
}
