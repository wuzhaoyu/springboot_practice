package com.wzy.service;

import com.wzy.dao.FilmMapper;
import com.wzy.entity.Film;
import com.wzy.queue.ReadWriteQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 类功能说明:
 *   沿用场景一的解决方案，为解决其缺陷，添加队列，凡是遇到写请求，
 *   则将写请求放入队列中，由队列对写请求统一管理，写请求处理成功，
 *   则从队列中删除。当有一个读请求过来时，到队列查询，是否有对应的写请求，
 *   如果有则放入队列中，等待写请求执行完之后再执行读请求。
 *   为防止某个请求阻塞情况，为其设置超时机制或者过期机制
 *
 * 类修改者	创建日期2020/12/14
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Service
public class ReadWriteConsistencyService {
    private ReadWriteQueue queue = new ReadWriteQueue();

    @Autowired
    private StringRedisTemplate template;


    @Autowired
    private FilmMapper filmMapper;

    /**
     * 读取数据库
     * @param key
     * @return
     */
    public String queryContext(String key) throws InterruptedException {
        // 读请求 判断队列中是否包含读请求
        // 包含
        // 不包含
        String s = template.opsForValue().get(key);
        return "返回" + s;
    }

    /**
     * 保存数据库
     * @param key
     * @return
     */
    public void saveContext(String key) throws InterruptedException {
        // 写发送到队列中
       ReadWriteQueue.WriteOperation writeOperation = queue.new WriteOperation(key);
        // 删除缓存
        template.delete(key);
        // 更新DB
        Film film = new Film();
        film.setName("测试" + key);
        filmMapper.insert(film);
        // 新增缓存
        template.opsForValue().set(key,"测试" + key);
        // 移除队列
        queue.poll();
    }

    public void dealQueue(){

    }

}
