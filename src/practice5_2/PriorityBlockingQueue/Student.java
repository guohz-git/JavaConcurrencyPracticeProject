package practice5_2.PriorityBlockingQueue;

public class Student implements  Comparable {

    private String name;
    private int age;
    private int num;

    public Student(String name, int age, int num) {
        this.name = name;
        this.age = age;
        this.num = num;
    }

    /**
     * 优先级比较方法
     * 算法是： 首先比较num ，num越大优先级越高
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        if(other.num > this.num){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", num=" + num +
                '}';
    }
}
