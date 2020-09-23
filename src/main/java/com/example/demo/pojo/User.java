package com.example.demo.pojo;



public class User {
    public int id;
    public String username;
    public String salt;
    public String password;
    public Integer status;

    public int getId() {
        return id;
    }

    public User(int id, String username, String password, Integer status, String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.salt = salt;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }







    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
