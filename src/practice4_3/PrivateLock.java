package practice4_3;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice4_3
 * @Date 2018/6/21 上午12:12
 * 通过内部类对象锁住部分代码
 */
public class PrivateLock {
    private final Object myLock = new Object();

    void someMethod() {
        synchronized (myLock) {
            //访问或修改Widget状态
        }
    }
}
