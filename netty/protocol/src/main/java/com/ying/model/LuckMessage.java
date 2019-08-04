package com.ying.model;

public class LuckMessage {
    private LuckHeader header;
    private String content;

    public LuckMessage() {
    }

    public LuckMessage(LuckHeader header, String content) {
        this.header = header;
        this.content = content;
    }

    public LuckHeader getHeader() {
        return header;
    }

    public void setHeader(LuckHeader header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LuckMessage{" +
                "header=" + header +
                ", content='" + content + '\'' +
                '}';
    }
}

