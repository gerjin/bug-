package com.example.demo.controll;

import com.example.demo.dao.userDao;
import com.example.demo.pojo.User;
import com.example.demo.service.userservice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api")
public class test {
    @Resource
    public userservice userservice;
    @RequestMapping("/getuser")
    @ResponseBody
    public User getuser(String username){
        return userservice.getuser(username);
    }
}
