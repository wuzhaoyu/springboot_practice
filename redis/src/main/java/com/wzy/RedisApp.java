package com.wzy;

import com.wzy.lock.RedisLockLua;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Controller
@Slf4j
@MapperScan({"com.wzy"})
public class RedisApp
{

    public static void main( String[] args )
    {
        SpringApplication.run(RedisApp.class);
    }

    @RequestMapping("/lock")
    public String lock(){
        RedisLockLua redisLockLua = new RedisLockLua();
        String today = redisLockLua.tryLock("today", "60");
        if(Objects.nonNull(today)){
            log.debug("获取所成功");
        }
        return "success";
    }

    @RequestMapping("/unlock")
    public String unlock(){
        RedisLockLua redisLockLua = new RedisLockLua();
        redisLockLua.unLock("today", "60");
        log.debug("获取所成功");
        System.out.println(String.format("%s 解锁所成功",Thread.currentThread().getId()));
        return "success";
    }
}
