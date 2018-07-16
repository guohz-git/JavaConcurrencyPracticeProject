package chapter7.log;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogService {
    private static final int CAPACITY = 3000;
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private boolean isShutdown;
    private int reservations;


    public LogService(Writer writer) {
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        logger = new LoggerThread(writer);
    }

    public void start(){
        this.logger.start();
    }

    /**
     * 修改标志变量，标志显示为中断状态
     */
    public void stop(){
        synchronized (this){
            isShutdown = true;
        }
        logger.interrupt();
    }

    public void log(String msg){
        synchronized (this){
            if(isShutdown){
                throw  new IllegalStateException("线程已经终止，不在打印日志");
            }
            ++reservations;
            queue.add(msg);
        }
    }

    private class LoggerThread extends Thread{
        private final PrintWriter writer;

        public LoggerThread(Writer writer) {
            this.writer = (PrintWriter) writer;
        }
        @Override
        public void run() {
            try {
                while (true){
                   synchronized (LogService.this){
                       //解释掉queue里面的元素退出执行
                       if(isShutdown && reservations == 0){
                           break;
                       }
                       String msg = queue.take();
                       writer.println(msg);
                       reservations--;
                   }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                writer.close();
            }
        }
    }
}
