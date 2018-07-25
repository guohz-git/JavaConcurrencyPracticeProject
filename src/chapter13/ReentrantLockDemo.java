package chapter13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {

        Lock lock  = new ReentrantLock();

        try{
            boolean b = lock.tryLock(1000, TimeUnit.MILLISECONDS);
            if(b){
                System.out.println("处理业务逻辑");
            }else{
                System.out.println("获取锁时间超时");
            }
        } catch (InterruptedException e) {
            System.out.println("获取锁中断");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
