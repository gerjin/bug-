package com.example.demo.dao;

import com.example.demo.pojo.FIlepojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface filedao {
    public void savefile( String name, String dir);
    public String getdir(String name);
    public List<String> getall();
}
