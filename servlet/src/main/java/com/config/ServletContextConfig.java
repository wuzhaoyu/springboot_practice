package com.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/27
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Component
public class ServletContextConfig implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration init = servletContext.addServlet("demoServlet",ServletDemo.class);
        init.addMapping("/demoServlet");
        init.setInitParameter("name","com.wzy");
    }
}
