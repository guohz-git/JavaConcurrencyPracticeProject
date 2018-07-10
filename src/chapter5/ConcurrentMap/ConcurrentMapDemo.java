package chapter5.ConcurrentMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapDemo {

    public static void main(String[] args) {
        ConcurrentMap<String,String> map =  new ConcurrentHashMap<>();

        map.put("key1","key1Value");
        map.put("key2","key2Value");

        String key1 = map.get("key1");
        System.out.println(key1);

    }
}
