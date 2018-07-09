package practice5.DelayedQueue;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.DelayedQueue
 * @Date 2018/7/3 下午11:30
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair() {
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }


    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
