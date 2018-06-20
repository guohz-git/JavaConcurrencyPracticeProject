package practice4_2;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author 郭浩柱 【351150710@qq.com】
 * practice4_2
 * @Date 2018/6/21 上午12:05
 * @TreadSafe
 */
public class PersonSet {
    //set集合不是线程安全的
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean containsPerson(Person person) {
        return mySet.contains(person);
    }


    class Person {
    }
}
