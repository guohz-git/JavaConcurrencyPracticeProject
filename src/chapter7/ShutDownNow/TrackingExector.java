package chapter7.ShutDownNow;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用装饰器模式，利用先用的类封装扩展功能
 */
public class TrackingExector extends AbstractExecutorService {

    private final ExecutorService exec;
    private final Set<Runnable> taskCanceledAtShowDown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExector() {
        this.exec = Executors.newCachedThreadPool();
    }

    @Override
    public void shutdown() {
        //强制终止方法
        exec.shutdownNow();
    }

    /**
     * 返回为执行的任务，不包括已经执行任务
     * @return
     */
    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout,unit);
    }

    @Override
    public void execute(Runnable command) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                }finally {
                    //如果线程已经关闭，将正在执行的任务返回
                    if(isShutdown()&& Thread.currentThread().isInterrupted()){
                        taskCanceledAtShowDown.add(command);
                    }
                }
            }
        });
    }

    /**
     * 返回正在执行取消的任务
     * @return
     */
    public List<Runnable> getCancelledTasks(){
        if(!exec.isTerminated()){
            throw new IllegalStateException("线程池没有结束，没有返回任务");
        }else{
            return new ArrayList<>(taskCanceledAtShowDown);
        }
    }
}
