package com.example.hotel.bean;

import java.io.Serializable;

/**
 * 订单
 */
public class OrderVo extends Orders implements Serializable {
    private String title;//标题
    private String image;//图片
    private String size;//房间大小
    private String bed;//床
    private Double price;//价格
    public OrderVo(Integer id, Integer roomId, Integer userId, String number, String startDate, String endDate, String orderDate, String name, String phone, String roomNum, String remark, Integer status,String idCard,Double price) {
        super(id, roomId, userId, number, startDate, endDate, orderDate, name, phone, roomNum, remark, status,idCard,price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
