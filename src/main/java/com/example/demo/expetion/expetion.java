package com.example.demo.expetion;

import com.example.demo.util.R;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class expetion {




    //捕获全局异常,处理所有不可知的异常
    @ExceptionHandler(value=UnauthorizedException.class)
    @ResponseBody
    R handleException(Exception e, HttpServletRequest request) {

            return R.build(400, "没有权限");

    }
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public R throwAuthenticationException(HttpServletRequest req,Exception e){
        return R.build(399,"验证出现问题，请重新登录");
    }
}