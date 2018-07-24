package chapter8.ThreadFactory;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private final static String poolName;
    public MyThreadFactory(String poolName) {
        poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
