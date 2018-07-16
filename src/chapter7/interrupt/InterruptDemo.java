package chapter7.interrupt;

public class InterruptDemo {

    public static void main(String[] args) {
        try {
            Thread t = new Thread(new Worker());
            t.start();
            Thread.sleep(2000);
            //手动终止子线程运行
            t.interrupt();
//            System.out.println("Main stop..");
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("main.收到子线程中断任务。");
            e.printStackTrace();
        }
    }

    public static class Worker implements  Runnable{

        @Override
        public void run() {
            while (!Thread.interrupted()){
                System.out.println("线程执行。。。。");
            }
            //                 System.out.println("线程开始运行!");
//                Thread curr = Thread.currentThread();
////                Thread.currentThread().interrupt();
//                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
//                curr.interrupt();
//                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
//                //clear status
//                System.out.println("Static Call: " + Thread.interrupted());
//                System.out.println("---------After Interrupt Status Cleared----------");
//                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
//                System.out.println("Static Call: " + Thread.interrupted());
//                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
        }
    }
}
