package practice5_2.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.CyclicBarrier
 * @Date 2018/7/2 下午11:11
 * 所有线程执行结束后等待执行时间，如果仍有任务没有执行结束则抛出异常线程继续执行
 */
public class CyclicBarrierThree {

    public static void main(String[] args) {
        int N = 4;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            if (i == (N - 1)) {
                //最后一个线程延迟执行
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new Writer(cyclicBarrier).start();
        }


    }

    /**
     * 模拟线程类
     */
    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据。。。。");

            try {
                Thread.sleep(2000);
                System.out.println("线程" + Thread.currentThread().getName() + "执行完毕。。。。");
                //线程进入等待状态，等待其它线程执行完毕
                cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            System.out.println("所有线程写入完毕，继续处理其它任务。");
        }
    }
}
