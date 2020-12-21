package com.wzy.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/15
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 * @description 说明：
 **/
public class ReadWriteQueue {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue();

    public class ReadOperation implements Runnable{

        private String key;

        public ReadOperation (String key) {
            this.key = key;
        }

        @Override
        public void run() {
            while (true){
                try {
                    queue.poll(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public class WriteOperation implements Runnable{

        private String key;

        public WriteOperation (String key) {
            this.key = key;
        }

        @Override
        public void run() {
            while (true){
                try {
                    queue.offer(key,5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  boolean isContain(String key){
        if(queue.contains(key)){
            return true;
        }
        return false;
    }
    public  String poll(){
       return queue.poll();
    }


}
