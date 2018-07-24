package chapter8.ThreadFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自定义线程类，实现对线程监控统计运行等信息
 */
public class MyThread extends Thread {
    public static String DEFAULT_NAME = "MyThread";
    public static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static Logger log = Logger.getAnonymousLogger();

    public MyThread(Runnable target, String name) {
        super(target, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE,"UNCAUGHT in thread "+t.getName(),e);
            }
        });
    }

    @Override
    public void run() {
        //复制debug标志确保一致性
        //防止同一个方法前后运行不一致
        boolean debug = debugLifecycle;
        if(debug){
            log.log(Level.FINE,"CREATED "+getName());
        }
        alive.incrementAndGet();
        super.run();
        if(debug){
            log.log(Level.FINE,"Exiting "+getName());
        }
    }

    public static int getThreadsCreated(){return created.get();}
    public static int getThreadsAlive(){return alive.get();}

}
