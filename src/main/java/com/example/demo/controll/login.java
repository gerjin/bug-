package com.example.demo.controll;


import com.alibaba.fastjson.JSON;
import com.example.demo.dao.filedao;
import com.example.demo.pojo.FIlepojo;
import com.example.demo.pojo.User;
import com.example.demo.service.userservice;
import com.example.demo.util.R;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class login {
    @Resource
    public com.example.demo.dao.filedao filedao;
    @Resource
    public userservice userservice;
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/login")
    public R  login(String username,String password){
        User user=userservice.getuser(username);
        if ("".equals(username.trim()) || "".equals(password.trim())) {
           return R.build(502,"不能为空");
        }
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            return R.build(502,"登录成功");
        } catch (AuthenticationException e) {
            return R.build(503,"登陆失败");
        }

    }
    @RequestMapping("/register")
    @ResponseBody
    public R  register(String username,String password){
       String salt="salt";
       password=new SimpleHash(Sha256Hash.ALGORITHM_NAME, password, ByteSource.Util.bytes(salt), 1024).toBase64();
        userservice.saveuser(username,password);
        return R.build(501,"注册成功");
    }
    @RequiresPermissions("op:write")
    @RequestMapping("/write")
    public R quanxian(){
        return R.build(401,"有读的权限");
    }

    @RequestMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file){
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路径
        String filePath = "d:/upload/";
        String Orignalname= fileName;
        // 文件重命名，防止重复
        fileName = filePath + fileName;
        filedao.savefile( Orignalname,fileName);
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            return R.build(404,"上传成功").put("src",fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.build(403,"上传失败").put("src",fileName);
    }
    @RequestMapping("/download")
    public R download(HttpServletResponse response,@Param("name") String name) {
       response.reset();
        String dir=filedao.getdir(name);
        System.out.println(dir);
        File file = new File(dir);
        FileInputStream fis=null;
        OutputStream os=null;
        try{
       fis = new FileInputStream(file);
        // 设置相关格式
            response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + dir);
        // 创建输出对象
         //清空缓存的内容
         os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
         return  R.build(123,"下载成功");
     } catch (IOException e) {
         e.printStackTrace();
         return  R.build(13,"下载失败");
     } finally{
         if (fis != null) {
             try {
                 fis.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         if (os != null) {
             try {
                 os.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
    }
    @RequestMapping("/show")
    @ResponseBody
    public List<String> show(){
         List<String> list=filedao.getall();
        return list;


    }


}
