package practice3_1_4;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice3_1_4
 * @Date 2018/6/8 下午11:42
 */
public class VolatileDemo {

    /**
     * volatile 用来将变量的更新操作及时通知到其它的线程
     */
    public volatile boolean flag = false;

    void run() {
        new ThreadOne().start();
        new ThreadTwo().start();
    }

    public static void main(String[] args) {
        System.out.println("test");


        VolatileDemo demo = new VolatileDemo();
        demo.run();
    }


    class ThreadOne extends Thread {
        @Override
        public void run() {
            while (!flag) {
                System.out.println("ThreadOne running ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadTwo extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(9000);
                //终止线程一继续执行
                flag = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
