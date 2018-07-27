package chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {
    private static final int BUFFER_SIZE = 1000;
    protected final Lock lock =  new ReentrantLock();
    private final Condition notFull = lock.newCondition();

    private final Condition notEmptyLock = lock.newCondition();
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    private int tail,head,count;

    public void put(T x) throws InterruptedException{
        lock.lock();
        try{
            //如果元素已经满了，则进行wait等待添加
            while(count == items.length){
                notFull.await();
            }
            items[tail] = x;
            //如果添加元素已经到最后一位则tail归0
            if(++tail == items.length){
                tail = 0;
            }
            count ++;
            //通知一个等待线程可以获取元素
            notEmptyLock.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take() throws  InterruptedException{
        lock.lock();
        try{
            while(count == 0){
                notEmptyLock.await();
            }
            T x = items[head];
            if(++head == items.length){
                head = 0;
            }
            count --;
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }
}
