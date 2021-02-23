package com.wzy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/13
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
