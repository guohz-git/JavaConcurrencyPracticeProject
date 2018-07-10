package chapter5.SynchronousQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Customer extends Thread {

    private SynchronousQueue queue;

    public Customer(SynchronousQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
           //每隔两秒消费一个任务
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(2);
                    Task task = (Task) queue.take();
                    System.out.println("消费任务："+ task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
