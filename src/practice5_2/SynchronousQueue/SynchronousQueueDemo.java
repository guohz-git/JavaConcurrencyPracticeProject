package practice5_2.SynchronousQueue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue queue = new SynchronousQueue();

        Thread producer = new Producer(queue);
        Thread customer = new Customer(queue);
        producer.start();
        customer.start();
    }
}
