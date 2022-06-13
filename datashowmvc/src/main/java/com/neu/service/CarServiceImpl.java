package com.neu.service;

import com.neu.mapper.CarMapper;
import com.neu.pojo.BookBean;
import com.neu.pojo.CarBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public int insertCar(BookBean bookInfo) {

        return carMapper.ins(bookInfo);
    }

    @Override
    public List<CarBean> getCarBookList() {

        return carMapper.getCarBookList();
    }
}
