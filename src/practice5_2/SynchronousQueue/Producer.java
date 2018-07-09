package practice5_2.SynchronousQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Producer extends Thread {

    private SynchronousQueue queue;

    public Producer(SynchronousQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("服务端线程启动。");
        //每隔1秒添加一个任务
        int i = 0;
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
                Task task = new Task("任务："+i);
                queue.put(task);
                System.out.println("添加任务："+ i +" 成功!");
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

