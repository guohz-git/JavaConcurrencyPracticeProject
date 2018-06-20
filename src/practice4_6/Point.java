package practice4_6;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice4_6
 * @Date 2018/6/21 上午12:21
 * person 是不可变对象，随意无需关心会影响并发安全
 */
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
