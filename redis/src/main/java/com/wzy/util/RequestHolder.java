package com.wzy.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/14
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
public class RequestHolder {

    public static HttpServletRequest getRequest(){
       return ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
