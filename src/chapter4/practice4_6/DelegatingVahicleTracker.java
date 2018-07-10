package chapter4.practice4_6;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * chapter4.practice4_6
 * @Date 2018/6/21 上午12:22
 * @TreadSafe 将线程类的安全性委托给，并发安全的属性变量从而实现并发安全性
 */
public class DelegatingVahicleTracker {

    private final ConcurrentMap<String, Point> locations;
    //之所以添加这个map结构是为了返回 列表的时候 ，把locations 原子容器封装好，不暴露给外部，以防篡改
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVahicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getPoint(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name : " + id);
        }
    }

}
