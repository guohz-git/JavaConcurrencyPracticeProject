package practice5.CoutDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.CoutDownLatch
 * @Date 2018/7/2 下午10:56
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        //CountDownLatch: 用法是等待指定线程执行后，调用的线程才会继续往下执行
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    latch.countDown();
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    latch.countDown();
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        try {
            System.out.println("等待两个线程执行完毕，继续执行。");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
