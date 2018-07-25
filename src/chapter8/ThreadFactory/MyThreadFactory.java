package chapter8.ThreadFactory;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        //可以在实例化线程时候完成线程监控
        return null;
    }
}
