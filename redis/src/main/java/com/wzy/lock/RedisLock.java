package com.wzy.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 类功能说明:
 *   简单锁实现，但是存在问题：
 *     1. 在执行 setnx(LOCK_KEY,"1")  是如果存在宕机，后面过期时间没有设置依然会导致死锁
 *     2. 在删除的key 进行解锁时 会把其他正在执行线程的key删除
 * 类修改者	创建日期2020/12/4
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
public class RedisLock {

    private static final JedisPool pool = new JedisPool("127.0.0.1", 6379);

    private static final String LOCK_KEY = "lock_key";
    // 获取锁之前的超时时间(获取锁的等待重试时间)
    private static final long acquireTimeout = 5000;
    // 超时时间
    private static final long TIMEOUT = 10000;

    public static boolean lock(){
        // 设置超时时间
        Jedis jedis = null;
        long endTime = System.currentTimeMillis() + acquireTimeout;
        // 加锁
        try {
            jedis = pool.getResource();
            jedis.select(7);
            while(System.currentTimeMillis() < endTime){
                System.out.println(String.format("%s线程正在重试",Thread.currentThread().getName()));
                String s = String.valueOf(endTime + TIMEOUT);
                if(jedis.setnx(LOCK_KEY,"1")  == 1){
                    // 加锁成功
                    int l = Long.valueOf(TIMEOUT).intValue();
                    // 设置到期时间 放置在调用unlock方法时出现异常，导致死锁
                    jedis.setex(LOCK_KEY, l ,"1");
                    return true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return false;

    }

    public static void unLock(){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(LOCK_KEY);
        }catch (Exception e){

        }finally {
            jedis.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            try {
                if(RedisLock.lock()){
                    System.out.println("今天 获取成功");
                }
            }finally {
                System.out.println("今天 解锁");
                RedisLock.unLock();
            }
        },"今天").start();
        Thread.sleep(5000);
        new Thread(()->{
            try {
                if(RedisLock.lock()){
                    System.out.println("明天 获取成功");
                }
            }finally {
                System.out.println("明天 解锁");
                RedisLock.unLock();
            }
        },"明天").start();
        Thread.sleep(5000);
        new Thread(()->{

            try {
                if(RedisLock.lock()){
                    System.out.println("后天 获取成功");
                }
            }finally {
                System.out.println("后天 解锁");
                RedisLock.unLock();
            }
        },"后天").start();
    }
}
