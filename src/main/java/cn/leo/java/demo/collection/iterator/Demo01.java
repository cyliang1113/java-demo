package cn.leo.java.demo.collection.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        arrayList();
//        linkedList();
    }

    public static void arrayList() throws InterruptedException {
        /**
         * ArrayList的iterator迭代器是怎么工作的 ?
         * 迭代器是ArrayList的内部类, 内部类访问外部类的属性
         */
        ArrayList<Integer> list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println("list1: " + list1);
        Iterator<Integer> iterator1 = list1.iterator();

        Thread thread = new Thread(() -> {
            list1.remove(2);
        });
        thread.start();
        thread.join();

        System.out.print("iterator1: ");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + ", ");
        }
        System.out.println();
    }

    public static void linkedList() throws InterruptedException {

        LinkedList<Integer> list1 = new LinkedList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println("list1: " + list1);
        Iterator<Integer> iterator1 = list1.iterator();

        Thread thread = new Thread(() -> {
            list1.remove(2);
        });
        thread.start();
        thread.join();

        System.out.print("iterator1: ");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + ", ");
        }
        System.out.println();
    }
}
