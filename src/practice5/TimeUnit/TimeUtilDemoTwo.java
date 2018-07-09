package practice5.TimeUnit;

import java.util.concurrent.TimeUnit;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.TimeUnit
 * @Date 2018/7/3 下午11:08
 */
public class TimeUtilDemoTwo {


    public static void main(String[] args) {
        //将1秒转化为毫秒
        long result = TimeUnit.SECONDS.toMillis(1);
        System.out.println(result);

        //将60秒转化为1分钟
        result = TimeUnit.SECONDS.toMinutes(60);
        System.out.println(result);
        //将1分钟转化为秒数
        result = TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);
        System.out.println(result);

        System.out.println(System.nanoTime());
    }
}
