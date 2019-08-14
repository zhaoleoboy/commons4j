package com.ying.hello;

@World(name = "type", gid = Long.class)
public class User {

    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @World(name = "user", gid = Long.class)
    public void testWorld() {
        System.out.println("tttttttttttttttttTTTTTTTT");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
