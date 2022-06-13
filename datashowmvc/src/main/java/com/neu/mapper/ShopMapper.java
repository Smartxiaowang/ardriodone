package com.neu.mapper;

import com.neu.pojo.BookBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ShopMapper {


    @Select("select * from book;")
    List<BookBean> getBookList();

    @Select("select * from book where id = #{bookid};")
    BookBean getBookInfo(String bookid);
}
