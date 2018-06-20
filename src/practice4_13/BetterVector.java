package practice4_13;

import java.util.Vector;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice4_13
 * @Date 2018/6/21 上午12:53
 */
public class BetterVector<E> extends Vector {

    public synchronized boolean pubIfAbsend(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;

    }

}
