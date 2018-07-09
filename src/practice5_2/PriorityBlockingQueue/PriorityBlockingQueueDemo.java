package practice5_2.PriorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 此Demo使用PriorityBlockingQueue 完成 num越大出队列越早
 * Student 类需要实现 compare 接口，自定义比较方法
 */
public class PriorityBlockingQueueDemo {


    public static void main(String[] args) throws InterruptedException {

        PriorityBlockingQueue queue = new PriorityBlockingQueue();

        Student stu = new Student("test100",100,100);
        Student stu2 = new Student("test102",102,102);
        Student stu3 = new Student("test99992",99992,99992);

        queue.put(stu);
        queue.put(stu2);
        queue.put(stu3);

        Student test = (Student) queue.take();
        System.out.println(test);
        test = (Student) queue.take();
        System.out.println(test);
        test = (Student) queue.take();
        System.out.println(test);
    }
}
