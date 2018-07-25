package chapter14;

public class GrumpyBoundBuffer<V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws BufferBoundException{
        if(isFull()){
            throw  new BufferBoundException();
        }
        doPost(v);
    }

    public synchronized V take() throws  BufferEmptyException{
        if(isEmpty()){
            throw new BufferEmptyException();
        }
        return doTake();
    }

}
