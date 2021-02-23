package com.wzy.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * 类功能说明: 改进redislock
 *   解决了 setnx 与setex 不是原子操作问题
 *   解决了 解锁时删除对应key
 *   简单锁实现，但是存在问题：
 *    不能解决所重入问题

 * 类修改者	创建日期2020/12/4
 * 修改说明
 *
 * @author com.com.com.wzy
 * @version V1.0
 * @description 说明：
 **/
public class RedisLock1 {

    private static final JedisPool pool = new JedisPool("127.0.0.1", 6379);

    private static final String LOCK_KEY = "lock_key";
    // 获取锁之前的超时时间(获取锁的等待重试时间)
    private static final long acquireTimeout = 990000;
    // 过期时间
    private static final int TIMEOUT = 500;

    private static final String SUCCESS = "OK";

    public  boolean lock(String requestId){
        // 设置条件
        SetParams set = new SetParams();
        set.nx().px(TIMEOUT);
        // 设置超时时间
        Jedis jedis = null;
        long endTime = System.currentTimeMillis() + acquireTimeout;
        // 加锁
        try {
            jedis = pool.getResource();
            jedis.select(7);
            for(;;){
                if(SUCCESS.equals(jedis.set(LOCK_KEY,requestId,set))){
                    System.out.println(String.format("%s线程获取成功",Thread.currentThread().getName()));
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                if(System.currentTimeMillis() > endTime){
                      return false;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return false;

    }

    public  void unLock(String requestId){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.select(7);
            if(jedis.get(LOCK_KEY).equals(requestId)){
                jedis.del(LOCK_KEY);
                System.out.println("释放锁..." + Thread.currentThread().getName() + ",identifierValue:" + requestId);
            }
        }catch (Exception e){

        }finally {
            jedis.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {


    }
}
