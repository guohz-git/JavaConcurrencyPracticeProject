package chapter15;

import java.util.concurrent.atomic.AtomicReference;

public class CurrentLinkedQueue<E> {
    private static class Node<E>{
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private final Node<E> dummy = new Node<>(null,null);
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item){
        Node<E> newNode = new Node<>(item,null);
        while (true){
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if(curTail == tail.get()){
                if(tailNext != null){
                    tail.compareAndSet(curTail,tailNext);
                }else{
                    //跟新下一个节点的值
                    if(curTail.next.compareAndSet(null,newNode)){
                        //更新next引用的值
                        tail.compareAndSet(curTail,newNode);
                        return true;
                    }
                }
            }
        }
    }

}
