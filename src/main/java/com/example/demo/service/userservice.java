package com.example.demo.service;

import com.example.demo.dao.userDao;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public interface userservice {
    public User getuser(String username);
    public void saveuser(String username,String password);
}
