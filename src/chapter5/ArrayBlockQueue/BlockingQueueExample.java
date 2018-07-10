package chapter5.ArrayBlockQueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2
 * @Date 2018/6/28 下午10:54
 */
public class BlockingQueueExample {
    static BlockingQueue queue = new ArrayBlockingQueue(1024);
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new ProducerThread();
        Thread consumer1 = new CustomerThread();
        Thread consumer2 = new CustomerThread();
        Thread consumer3 = new CustomerThread();
        Thread consumer4 = new CustomerThread();

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        Thread.sleep(10000);

    }


    static class ProducerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("线程执行。。。。");
                try {
                    queue.put(i++);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class CustomerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    int result = (int) queue.take();
                    System.out.println(Thread.currentThread().getName() + " : " + result);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
