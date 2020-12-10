package com.wzy.lock;

import com.wzy.config.SpringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;

/**
 * 类功能说明:  使用lua 脚本实现
 * 类修改者	创建日期2020/12/5
 * 修改说明
 *   解决锁重入问题，保证原子性
 *   数据格式hash : key ： { hashKey : value ,.....}
 *   步骤：
 *    一 ： 获取锁
 *      1. 判断是否存在 lock_key
 *       - 1) 不存在 直接创建 lock_key 并且 在hashKey(可使用当前线程ID),value为一，加上自动释放锁时间
 *       - 2) 存在情况 判断hashKey是否当前线程的ID
 *          - 1） 不是 ，则获取锁失败
 *          - 2） 是，则在hashKey 的value上加一 ，并且重置自动释放时间
 *
 *     二： 释放锁
 *       1. 判断是否存在重入 ，也就是判断value值 减一后是否为 0
 *         - 1） 是 则删除 lock_key
 *         - 2) 否 则在重入的计数的value上减一
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class RedisLockLua {

    private static  StringRedisTemplate redisTemplate = SpringUtils.getBean(StringRedisTemplate.class);
    private static final DefaultRedisScript<Long> LOCKREDISSCRIPT ;
    private static final DefaultRedisScript UNLOCKREDISSCRIPT ;

    // 获取锁之前的超时时间(获取锁的等待重试时间)
    private static final long acquireTimeout = 990000;
    static {

        // 获取锁
        LOCKREDISSCRIPT =  new DefaultRedisScript<>();
        LOCKREDISSCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
        LOCKREDISSCRIPT.setResultType(Long.class);

        // 释放锁
        UNLOCKREDISSCRIPT = new DefaultRedisScript();
        UNLOCKREDISSCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
    }
    /**
     * 获取锁
     * @param lockName 锁名称
     * @param releaseTime 超时时间(单位:秒)
     * @return key 解锁标识
     */
    public  boolean tryLock(String lockName,String releaseTime){
        // 加入UUID 防止其他JVM中线程ID重复
        String key =String.valueOf(Thread.currentThread().getName());

        long endTime = System.currentTimeMillis() + acquireTimeout;
        for(;;){
            Long result = redisTemplate.execute(LOCKREDISSCRIPT, Collections.singletonList(lockName),key,releaseTime);
            if(result !=null && result.intValue() == 1){
                System.out.println(String.format("%s线程获取成功",Thread.currentThread().getName()));
                return true;
            }
            //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
            if(System.currentTimeMillis() > endTime){
                return false;
            }
        }
    }

    public  void unLock(String lockName){
        // 执行脚本
        redisTemplate.execute(
                UNLOCKREDISSCRIPT,
                Collections.singletonList(lockName),
                Thread.currentThread().getName(), null);
        System.out.println("释放锁..." + Thread.currentThread().getName() );
    }


}
