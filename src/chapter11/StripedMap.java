package chapter11;

import java.util.Arrays;

public class StripedMap {
    //由locks[n%N_lOCKS]来保护
    private static final int N_LOCKS = 16;

    private final Node[] buckets;
    private Object[] locks;

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        //实例化出锁定对象
        for(int i = 0 ; i < N_LOCKS; i++){
            locks[i] = new Object();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StripedMap that = (StripedMap) o;
        return Arrays.equals(buckets, that.buckets) &&
                Arrays.equals(locks, that.locks);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(buckets);
        result = 31 * result + Arrays.hashCode(locks);
        return result;
    }

    public final int hash(Object key){
      return Math.abs(key.hashCode() & buckets.length);
    }

    public Object get(Object key){
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]){
            for(Node m = buckets[hash]; m != null ; m = m.next){
                if(m.key.equals(key)){
                    return m.value;
                }
            }
        }
        return null;
    }
    public void clear(){
        for(int i = 0 ; i < buckets.length; i++){
            synchronized (locks[i % N_LOCKS]){
                buckets[i] = null;
            }
        }
    }
    private static class Node{
        public Node next;
        public Object key;
        public Object value;
    }
 }
