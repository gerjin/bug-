package com.example.demo.util;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class R implements Serializable {

    private int code;
    private String msg;
    private Map<String,String> data;

     private R(int code, String msg, Map <String,String> data) {
        this.code = code;
         this.msg = msg;
        this.data = data;  }

    public static R success(){
         return new R(200,"success",null);
     }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public static R fail(){
         return new R(500,"fail",null);
     }

      public static R build(int code,String msg){
         return new R(500,msg,null);
     }
     public  R put(String key,String value){
         Map<String,String> map=new HashMap<>();
         map.put(key,value);
         this.setData(map);
         return this;
     }
   }
