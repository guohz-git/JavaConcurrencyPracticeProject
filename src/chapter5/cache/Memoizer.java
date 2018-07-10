package chapter5.cache;

import java.util.concurrent.*;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * 构建高性能，高扩展，并发安全的线程缓存类
 * practice5_2.cache
 * @Date 2018/7/9 下午11:44
 */
public class Memoizer<K, V> implements Computable<K, V> {
    private final ConcurrentMap<K, Future<V>> cache =
            new ConcurrentHashMap<>();
    private final Computable<K, V> c;

    public Memoizer(Computable<K, V> c) {
        this.c = c;
    }

    @Override
    public V compute(K args) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(args);
            if (future == null) {

                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        //异步开始计算任务
                        return c.compute(args);
                    }
                };

                FutureTask<V> ft = new FutureTask<V>(callable);
                //如果args不存map里面则会返回null值
                future = cache.putIfAbsent(args, ft);

                //进行判断，如果没有计算，则启动线程计算
                if (future == null) {
                    future = ft;
                    ft.run();
                }

            }

            try {
                return future.get();
            } catch (ExecutionException e) {
                throw new InterruptedException();
            }
        }
    }
}
