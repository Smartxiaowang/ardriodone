package com.neu.service;

import com.neu.mapper.ShopMapper;
import com.neu.pojo.BookBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;


    @Override
    public List<BookBean> getBookList() {
        return shopMapper.getBookList();
    }

    @Override
    public BookBean getBookInfo(String bookid) {
        return shopMapper.getBookInfo(bookid);
    }
}
