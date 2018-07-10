package chapter6.CompletionRenderPage;

/**
 * 优先级队列元素，优先出列下载完成的图片异步任务
 * @param <V>
 */
public class QueueFuture<V> implements Comparable{

    private final QueueingFutureTask<V> future;

    public QueueFuture(QueueingFutureTask<V> future) {
        this.future = future;
    }

    @Override
    public int compareTo(Object o) {
        QueueFuture<V> other = (QueueFuture<V>) o;
        if(o == this){
            return 0;
        }
        //如果两个都已经完成则比较索引
        if(future.isDone() && other.getFuture().isDone()){
            return future.getIndex() > other.getFuture().getIndex() ? 1 : -1;
        }else if(future.isDone()){
            return 1;
        }else{
            return -1;
        }
    }

    public QueueingFutureTask<V> getFuture() {
        return future;
    }
}
