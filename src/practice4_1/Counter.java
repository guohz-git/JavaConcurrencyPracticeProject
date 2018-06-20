package practice4_1;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice4_1
 * @Date 2018/6/20 下午11:51
 * @ThreadSafe 因为该类操作共享变量 value 无论增加和获取都用同步锁，所以该类是线程安全的。
 */

public class Counter {
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
        return ++value;
    }
}
