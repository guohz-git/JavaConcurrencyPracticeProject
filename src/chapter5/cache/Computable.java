package chapter5.cache;

public interface Computable<K, V> {

    V compute(K args)  throws InterruptedException;
}
