package com.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/23
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public String errorHandle(AuthenticationException e){
        log.error("权限认证失败",e);
        return "权限认证失败";
    }
}
