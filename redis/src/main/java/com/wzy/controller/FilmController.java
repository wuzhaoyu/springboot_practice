package com.wzy.controller;

import com.wzy.dao.FilmMapper;
import com.wzy.entity.Film;
import com.wzy.lock.RedisLockLua;
import com.wzy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/8
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Controller
public class FilmController {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private FilmMapper filmMapper;

    private static  Integer STOCK = 0;

    private static RedisLockLua redisLockLua = new RedisLockLua();

    /**
     * 使用消息队列的方式 使用大量请求快速返回，对数据库的操作延后进行。
     * 同时使用请求的消息进入队列中，有序的进行
     * @return
     */
    @RequestMapping(value = "/stock")
    @ResponseBody
    public String inset(){
        template.convertAndSend("stock","1");
        return "success";
    }

    /**
     * 使用消息队列的方式 使用大量请求快速返回，对数据库的操作延后进行。
     * 同时使用请求的消息进入队列中，有序的进行
     * @return
     */
    @RequestMapping(value = "/testLock")
    @ResponseBody
    public String testLock(){
       /* Executors.newFixedThreadPool(20);
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, blockingQueue);
        for (int i = 0; i < 100000; i++) {
            executor.execute(()->{
                filmMapper.stockOperate(1L);
            });
        }*/
      /* synchronized (redisLockLua){
           filmMapper.stockOperate(1L);
           System.out.println(++STOCK);
        }*/

       /* if(Objects.nonNull(today)){
            filmMapper.stockOperate(1L);
            System.out.println(++STOCK);
        }*/
        /*AccountService accountRedisLock = new AccountService.AccountRedisLock(10000);
        AccountService.demo(accountRedisLock);*/
        template.opsForValue().set(("wzy"+ STOCK++),"1");

       return "success";
    }
}
