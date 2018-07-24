package chapter8.policy;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PolicyDemo {

    private static final int threads = 10;
    private static final int m_threads = 20;
    private static final int capacity = 200;

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(threads,m_threads,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(capacity));

    public static void main(String[] args) {
        //设置调用运行拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("一致性。。");
            }
        });
    }

}
