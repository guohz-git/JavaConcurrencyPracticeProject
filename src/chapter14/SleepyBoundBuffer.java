package chapter14;

public class SleepyBoundBuffer<V> extends BaseBoundedBuffer<V> {
    private static final long SLEEP_GRANULARITY = 1000;

    public SleepyBoundBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws  InterruptedException {
        while (true){
            synchronized (this){
                if(!isFull()){
                    doPost(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public synchronized V take() throws InterruptedException {
        while (true){
            synchronized (this){
                if(!isEmpty()){
                   return take();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }

    }


}
