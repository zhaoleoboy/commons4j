package com.ying.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 4055972567716895964L;

    private String name;
    private String sex;
    private int age;


}
