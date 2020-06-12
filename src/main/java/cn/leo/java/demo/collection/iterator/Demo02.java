package cn.leo.java.demo.collection.iterator;

import java.util.*;

public class Demo02 {
    public static void main(String[] args) throws InterruptedException {
        hashmapEntrySetIterator();

    }

    public static void hashmapEntrySetIterator() throws InterruptedException {
        /**
         * HashMap的iterator迭代器是怎么工作的 ?
         * 迭代器是HashMap的内部类, 内部类访问外部类的属性
         */
        HashMap<Integer, Integer> hashmap1 = new HashMap();
        hashmap1.put(1, 11);
        hashmap1.put(2, 22);
        hashmap1.put(3, 33);
        System.out.println("hashmap1: " + hashmap1);
        Set<Map.Entry<Integer, Integer>> entries = hashmap1.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator1 = entries.iterator();

        Thread thread = new Thread(() -> {
            hashmap1.remove(2);
        });
        thread.start();
        thread.join();

        System.out.print("iterator1: ");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + ", ");
        }
        System.out.println();
    }

    public static void hashmapKeySetIterator() throws InterruptedException {
        HashMap<Integer, Integer> hashmap1 = new HashMap();
        hashmap1.put(1, 11);
        hashmap1.put(2, 22);
        hashmap1.put(3, 33);
        System.out.println("hashmap1: " + hashmap1);
        Set<Integer> set = hashmap1.keySet();
        Iterator<Integer> iterator1 = set.iterator();

        Thread thread = new Thread(() -> {
            hashmap1.remove(2);
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
