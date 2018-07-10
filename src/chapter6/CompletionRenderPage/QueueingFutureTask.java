package chapter6.CompletionRenderPage;

import com.sun.scenario.effect.ImageData;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

public class QueueingFutureTask<V> extends FutureTask<V> {

    /**
     * 任务索引
     */
    private long index;
    private static final AtomicLong SEQUENCE = new AtomicLong();
    BlockingQueue completionQueue;

    public QueueingFutureTask(Callable<V> callable, BlockingQueue  completionQueue) {
        super(callable);
        this.completionQueue = completionQueue;
        index = SEQUENCE.incrementAndGet();
    }

    public QueueingFutureTask(Runnable runnable, V result) {
        super(runnable, result);
    }

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


