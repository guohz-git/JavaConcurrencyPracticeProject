package chapter2.practice2_5;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UnsafeCachingFactorizer {
    private final AtomicReference<BigInteger> lastNamber
            = new AtomicReference<>();

    private final AtomicReference<BigInteger []> lastFactores
            = new AtomicReference<>();

    public  void service(BigInteger i){

        if( i .equals(lastNamber.get())){
            System.out.println(lastFactores.get());
        }else{
            BigInteger[] factors = new BigInteger[10];
            factors[0] = i;

            lastNamber.set(i);
            lastFactores.set(factors);

        }
    }

    public static void main(String[] args) {
        UnsafeCachingFactorizer obj = new UnsafeCachingFactorizer();
        obj.service(BigInteger.valueOf(0L));
    }
}
