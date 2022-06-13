package com.neu.mapper;


import com.neu.pojo.BookBean;
import com.neu.pojo.CarBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CarMapper {

    @Insert("INSERT INTO shopcar (author,isbn,bookname,price,bookid) VALUES (#{author}" +
            ",#{isbn}," +
            "#{bookname}," +
            "#{price}," +
            "#{id})")
    int ins(BookBean bookInfo);

    @Select("select * from shopcar")
    List<CarBean> getCarBookList();
}
