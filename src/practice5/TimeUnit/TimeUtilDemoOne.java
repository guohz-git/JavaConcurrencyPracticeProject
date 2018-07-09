package practice5.TimeUnit;

import java.util.concurrent.TimeUnit;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.TimeUnit
 * @Date 2018/7/3 下午11:02
 * <p>
 * TimeUnit 类似于 Thread.sleep 最大的好处就是提升程序的可读性，单位转换比较灵活
 */
public class TimeUtilDemoOne {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Sleeping for 4 seconds using Thread.sleep");

        Thread.sleep(4 * 1000);
        System.out.println("Sleeping for 4 seconds using TimeUnit");
        TimeUnit.SECONDS.sleep(4);

        TimeUnit.MINUTES.sleep(4);

        TimeUnit.HOURS.sleep(4);

        TimeUnit.DAYS.sleep(1);
    }
}
