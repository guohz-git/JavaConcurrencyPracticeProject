package chapter6.CompletionRenderPage;

import com.sun.scenario.effect.ImageData;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 异步任务对象
 * 异步化执行下载图片任务，每个任务都有一个唯一索引
 * @param <V>
 */
public class QueueingFutureTask<V> extends FutureTask<V> {

    /**
     * 任务索引
     */
    private long index;
    /**
     * 生成索引对象
     */
    private static final AtomicLong SEQUENCE = new AtomicLong();
    /**
     * 保存任务阻塞对象
     */
    BlockingQueue completionQueue;

    public QueueingFutureTask(Callable<V> callable, BlockingQueue  completionQueue) {
        super(callable);
        this.completionQueue = completionQueue;
        index = SEQUENCE.incrementAndGet();
    }

    public QueueingFutureTask(Runnable runnable, V result) {
        super(runnable, result);
    }

    /**
     * 当异步任务完成，将任务放在队列里面
     */
    @Override
    protected void done(){
        //将已完成的任务放入队列
        try {
            QueueFuture<V> queue = new QueueFuture<>(this);
            completionQueue.put(queue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getIndex() {
        return index;
    }

}


