package chapter5.DelayedQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.DelayedQueue
 * @Date 2018/7/3 下午11:32
 */
public class DelayItem<T> implements Delayed {

    /**
     * 获取类初始化的时候纳秒值
     */
    private static final long NANO_ORIGIN = System.nanoTime();


    /**
     * 给延迟任务类一个索引数值
     */
    private static final AtomicLong SEQUENCE = new AtomicLong();
    private final long sequenceNumber;

    /**
     * 延迟类延迟的数值
     */
    private final long time;

    private final T item;

    public DelayItem(T submit, long timeout) {
        this.time = now() + timeout;
        this.item = submit;
        this.sequenceNumber = SEQUENCE.getAndIncrement();
    }

    public T getItem() {
        return this.item;
    }

    /**
     * 获取当前时间的纳秒值 - 初始化的纳秒值
     *
     * @return long数据类型结果
     */

    final static long now() {
        return System.nanoTime() - NANO_ORIGIN;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //计算延迟是否超时
        long d = unit.convert(time - now(), TimeUnit.NANOSECONDS);
        return d;
    }

    /**
     * 比较两个延迟类是否相等
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Delayed other) {
        // compare zero ONLY if same object
        if (other == this) {
            return 0;
        }
        if (other instanceof DelayItem) {
            DelayItem x = (DelayItem) other;
            long diff = time - x.time;
            if (diff < 0) {
                return -1;
            } else if (diff > 0) {
                return 1;
            } else if (sequenceNumber < x.sequenceNumber) {
                return -1;
            } else {
                return 1;
            }
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));

        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }


}
