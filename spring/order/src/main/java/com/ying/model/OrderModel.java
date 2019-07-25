package com.ying.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderModel {

    private Long id;
    private Date createTime;
    private Date payTime;
}
