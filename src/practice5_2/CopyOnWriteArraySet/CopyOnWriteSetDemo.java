package practice5_2.CopyOnWriteArraySet;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteSetDemo {
    public static void main(String[] args) {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();
        set.add("1");
        set.add("1");
        set.add("2");

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            String test = (String) iterator.next();
            System.out.println(test);
        }
    }
}
