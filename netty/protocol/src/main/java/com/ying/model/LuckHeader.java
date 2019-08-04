package com.ying.model;

public class LuckHeader {
    private int version;
    private int length;
    private String session;

    public LuckHeader() {
    }

    public LuckHeader(int version, int length, String session) {
        this.version = version;
        this.length = length;
        this.session = session;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
