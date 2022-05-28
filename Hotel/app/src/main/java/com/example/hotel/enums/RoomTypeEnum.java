package com.example.hotel.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间类型（1:单人间 2:双人间）
 */
public enum RoomTypeEnum {
    A1 ("单人间", 1),
    A2 ("双人间", 2),

    ;

    // 成员变量
    private String desc;
    private int code;

    // 构造方法
    private RoomTypeEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    // 普通方法
    public static String getName(Integer code) {
        for (RoomTypeEnum c : RoomTypeEnum.values()) {
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
        for (RoomTypeEnum statusEnum : RoomTypeEnum.values()) {
            list.add(statusEnum.getDesc());
        }
        return list;
    }
    public static List<Integer> getCodeList() {
        List<Integer> list = new ArrayList<>();
        for (RoomTypeEnum statusEnum : RoomTypeEnum.values()) {
            list.add(statusEnum.getCode());
        }
        return list;
    }
}



