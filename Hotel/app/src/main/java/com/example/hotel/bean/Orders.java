package com.example.hotel.bean;

import java.io.Serializable;

/**
 * 订单
 */
public class Orders implements Serializable {
    private Integer id;//
    private Integer roomId;//房间ID
    private Integer userId;//用户ID
    private String number;//编号
    private String startDate;//开始日期
    private String endDate;//结算日期
    private String orderDate;//下单日期
    private String name;//姓名
    private String phone;//电话
    private String roomNum;//房间数量
    private String remark;//备注
    private Integer status;//状态
    private String idCard;//身份证
    private Double price;//价格

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Orders(Integer id, Integer roomId, Integer userId, String number, String startDate, String endDate, String orderDate, String name, String phone, String roomNum, String remark, Integer status, String idCard,Double price) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderDate = orderDate;
        this.name = name;
        this.phone = phone;
        this.roomNum = roomNum;
        this.remark = remark;
        this.status = status;
        this.idCard = idCard;
        this.price = price;
    }
}
