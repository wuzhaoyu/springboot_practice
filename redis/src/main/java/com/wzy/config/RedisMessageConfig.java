package com.wzy.config;

import com.wzy.message.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 类功能说明:  配置redis消息队列
 * 类修改者	创建日期2020/12/8
 * 修改说明
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
@Configuration
@Slf4j
public class RedisMessageConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(connectionFactory);
        // 定义订阅通道
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter,new PatternTopic("stock"));
        return redisMessageListenerContainer;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        // 监听类与方法
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }

}
