package com.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/9/18
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Configuration
public class ServletConfig  {
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ServletExtend(),"/servletExtend");
        return servletRegistrationBean;
    }
}
