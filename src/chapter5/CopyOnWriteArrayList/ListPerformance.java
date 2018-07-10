package chapter5.CopyOnWriteArrayList;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class ListPerformance {

    private final int threadNumber;

    public ListPerformance(int num){
        this.threadNumber = num;
    }

    private static class TestThread implements  Runnable{
        private static long totalTime;
        private final int No;
        private static final int loop = 100000;
        private final Thread t;
        private final List<Integer> list;
        private CountDownLatch countDownLatch;

        TestThread(int No, List<Integer> list,CountDownLatch countDownLatch) {
            this.No = No;
            this.list = list;
            t = new Thread(this);
            this.countDownLatch = countDownLatch;
        }

        public void start() {
            t.start();
        }

        public synchronized void addTime(long time) {
            totalTime += time;
        }

        @Override
        public void run() {
            long time = randomAccess();
            addTime(time);
            this.countDownLatch.countDown();
        }

        @Override
        public String toString() {
            return "Thread " + No + ":";
        }

        public long randomAccess() {
            Date date1 = new Date();
            Random random = new Random();
            for (int i = 0; i < loop; i++) {
                int n = random.nextInt(loop);
                Integer integer = list.get(n);
            }
            Date date2 = new Date();
            long time = date2.getTime() - date1.getTime();
            return time;

        }
    }


    public void initList(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(new Integer(i));
        }
    }

    /**
     * 构建线程随机读取数组元素
     * @param list
     */
    public void test(List<Integer> list,CountDownLatch countDownLatch) {
        System.out.println("Test List Performance");
        TestThread[] ts = new TestThread[threadNumber];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new TestThread(i, list,countDownLatch);
        }
        for (int i = 0; i < ts.length; i++) {
            ts[i].start();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(64);
        ListPerformance lp = new ListPerformance(64);
        List<Integer> al = Collections
                .synchronizedList(new ArrayList<Integer>());
        lp.initList(al, 10000000);
        lp.test(al,countDownLatch);
        countDownLatch.await();
        System.out.println(al.size());
        System.out.println("执行时间:"+TestThread.totalTime);

        countDownLatch = new CountDownLatch(64);
        TestThread.totalTime = 0;
        CopyOnWriteArrayList<Integer> cl = new CopyOnWriteArrayList<Integer>(al);
        lp.test(cl,countDownLatch);
        countDownLatch.await();
        System.out.println(cl.size());
        System.out.println("执行时间:"+TestThread.totalTime);
    }


}
