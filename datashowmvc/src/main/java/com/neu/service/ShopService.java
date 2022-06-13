package com.neu.service;

import com.neu.pojo.BookBean;

import java.util.List;

/**
 * 用户管理逻辑类
 * @author Admin
 *
 */
public interface ShopService {

    List<BookBean> getBookList();

    BookBean getBookInfo(String bookid);
}
