package chapter5.DelayedQueue;

import java.util.concurrent.TimeUnit;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.DelayedQueue
 * @Date 2018/7/4 上午12:12
 */
public class DelayedQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        Cache<Integer, String> cache = new Cache<>();
        cache.put(1, "aaa", 3, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);
        String s = cache.get(1);
        System.out.println(s);
        TimeUnit.SECONDS.sleep(5);
        s = cache.get(1);
        System.out.println(s);
    }
}
