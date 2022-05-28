package com.example.hotel.bean;

import java.io.Serializable;

/**
 * 房间
 */
public class Room implements Serializable {
    private Integer id;//
    private String title;//房间号
    private String image;//图片
    private String size;//房间大小
    private String bed;//床
    private Integer quantity;//总数量
    private Integer surplus;//剩余数量
    private Double price;//价格
    private String remark;//备注
    private Integer typeId;//类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Room(Integer id, String title, String image, String size, String bed, Integer quantity, Integer surplus, Double price, String remark, Integer typeId) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.size = size;
        this.bed = bed;
        this.quantity = quantity;
        this.surplus = surplus;
        this.price = price;
        this.remark = remark;
        this.typeId = typeId;
    }
}
