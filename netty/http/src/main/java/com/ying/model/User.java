package com.ying.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private Date birth;
}
