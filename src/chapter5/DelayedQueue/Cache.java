package chapter5.DelayedQueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.DelayedQueue
 * @Date 2018/7/3 下午11:54
 * Cache 缓存实现类
 */
public class Cache<K, V> {
    private static final Logger LOG = Logger.getLogger(Cache.class.getName());

    private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<>();

    private DelayQueue<DelayItem<Pair<K, V>>> q = new DelayQueue<>();

    /**
     * cache守护线程，用于定时检测类是否过期
     */
    private Thread daemonThread;

    /**
     * 初始化内部实现类
     */
    public Cache() {
        Runnable daemonTask = new Runnable() {
            @Override
            public void run() {
                daemonCheck();
            }
        };

        daemonThread = new Thread(daemonTask);
        daemonThread.setDaemon(true);
        daemonThread.setName("Cache Daemon");
        daemonThread.start();
    }

    /**
     * 定期检测缓存是否失效
     */
    private void daemonCheck() {
        //检测当前日志等级
        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("cache service stated.");
        }
        //死循环
        for (; ; ) {
            try {
                //如果有超时的对象则从 cache保存的Map中移除
                DelayItem<Pair<K, V>> delayItem = q.take();
                if (delayItem != null) {
                    Pair<K, V> pair = delayItem.getItem();
                    cacheObjMap.remove(pair.getKey());
                }
            } catch (InterruptedException e) {
                //如果出现异常终止 cache检测线程继续执行
                if (LOG.isLoggable(Level.SEVERE)) {
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                }
                break;
            }

        }

        //如果出循环则说明 守护线程出现异常
        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("cache service stopped.");
        }
    }

    /**
     * 添加缓存对象
     *
     * @param key     缓存KEY值
     * @param value   缓存Value值
     * @param timeout 超时时间数值
     * @param unit    超时时间单位
     */
    public void put(K key, V value, long timeout, TimeUnit unit) {
        V oldValue = cacheObjMap.put(key, value);
        ///??
        if (oldValue != null) {
            q.remove(key);
        }
        long nanoTime = TimeUnit.NANOSECONDS.convert(timeout, unit);
        q.put(new DelayItem<Pair<K, V>>(new Pair<K, V>(key, value), nanoTime));
    }

    public V get(K key) {
        return cacheObjMap.get(key);
    }

}
