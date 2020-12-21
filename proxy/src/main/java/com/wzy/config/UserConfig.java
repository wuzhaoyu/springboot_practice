package com.wzy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.wzy.proxy.UserIntroducerAspect;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/19
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@ComponentScan(value = "com.wzy.dao")
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class UserConfig {
   /* @Bean
    public UserIntroducerAspect userIntroducerAspect() {
        return new UserIntroducerAspect();
    }*/
}
