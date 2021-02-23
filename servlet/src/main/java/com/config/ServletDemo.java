package com.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/27
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
public class ServletDemo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DemoServlet" + getServletConfig().getInitParameter("name"));
        resp.sendError(501, "错误");
    }

}
