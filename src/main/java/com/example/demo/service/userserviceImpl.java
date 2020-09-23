package com.example.demo.service;

import com.example.demo.dao.userDao;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class userserviceImpl implements userservice {

    @Resource
    public userDao userdaoL;
    @Override
    public User getuser(String username) {
        return userdaoL.getByUsername(username);

    }

    @Override
    public void saveuser(String username, String password) {
        userdaoL.saveuser(username,password);
    }
}
