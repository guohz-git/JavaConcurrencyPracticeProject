package practice5.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.CyclicBarrier
 * @Date 2018/7/2 下午11:11
 * CyclicBarrier 是可以重新利用的
 */
public class CyclicBarrierFour {

    public static void main(String[] args) {
        int N = 4;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Writer(cyclicBarrier).start();
        }


        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < N; i++) {
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
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("所有线程写入完毕，继续处理其它任务。");
        }
    }
}
