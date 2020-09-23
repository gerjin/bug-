package com.example.demo.pojo;

public class FIlepojo {
    public int id;
    public String name;
    public String dir;

    public FIlepojo(int id, String name, String dir) {
        this.id = id;
        this.name = name;
        this.dir = dir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public FIlepojo() {
    }
}
