package com.example.hotel.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态（0:预定房间 1:退订房间 2:办理入住 3:办理退房）
 */
public enum OrderStatusEnum {
    Reserve ("预定房间", 0),
    Unsubscribe ("退订房间", 1),
    CheckingIn ("办理入住", 2),
    CheckingOut ("办理退房", 3),

    ;

    // 成员变量
    private String desc;
    private int code;

    // 构造方法
    private OrderStatusEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    // 普通方法
    public static String getName(Integer code) {
        for (OrderStatusEnum c : OrderStatusEnum.values()) {
            if (c.getCode() == code) {
                return c.desc;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取列表
     * @return
     */
    public static List<String> getNameList() {
        List<String> list = new ArrayList<>();
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            list.add(statusEnum.getDesc());
        }
        return list;
    }
    public static List<Integer> getCodeList() {
        List<Integer> list = new ArrayList<>();
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            list.add(statusEnum.getCode());
        }
        return list;
    }

}



