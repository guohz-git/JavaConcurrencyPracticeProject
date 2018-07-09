package practice5.BlockingQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.BlockingQueue
 * @Date 2018/6/28 下午11:29
 */
public class DelayedElement implements Delayed {
    @Override
    public long getDelay(TimeUnit unit) {
        unit.toSeconds(1000);
        return 1000;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
