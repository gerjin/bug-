package com.example.demo.dao;


import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface userDao {
   public User getByUsername(String username) ;
   public void saveuser(@Param("username") String username, @Param("password") String password);
}
