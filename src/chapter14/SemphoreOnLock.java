package chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemphoreOnLock {

    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvaliable = lock.newCondition();
    private int permits;

    SemphoreOnLock(int initalPermits){
        lock.lock();
        try {
            permits = initalPermits;
        }finally {
            lock.unlock();
        }
    }

    public void acquire()throws InterruptedException{
        lock.lock();
        try {
            while (permits <= 0 ){
                permitsAvaliable.await();
            }
            --permits;
        }finally {
            lock.unlock();
        }
    }

    public void release(){
        lock.lock();
        try {
            ++permits;
            permitsAvaliable.signal();
        }finally {
            lock.unlock();
        }
    }

}
