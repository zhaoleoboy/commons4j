package com.ying.model;

import java.io.Serializable;

public class Data implements Serializable {

    private static final long serialVersionUID = -2429902941422138259L;

    private long id;
    private String name;

    public Data() {
    }

    public Data(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
