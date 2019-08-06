package com.ying.model;


public class JsonInfo {

    /**
     * 测试属性全部大写时，fastjson序列化时的坑
     */
    private String NAME;

    public JsonInfo() {
    }

    public JsonInfo(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
