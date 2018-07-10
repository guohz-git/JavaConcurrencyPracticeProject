package chapter5.cache;

import java.math.BigInteger;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice5_2.cache
 * @Date 2018/7/9 下午11:46
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String args) throws InterruptedException {
        return new BigInteger(args);
    }
}
