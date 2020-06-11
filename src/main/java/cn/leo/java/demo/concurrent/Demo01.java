package cn.leo.java.demo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo01 {
    public static void main(String[] args) {
        /**
         * AtomicInteger incrementAndGet 超过最大值 会怎么样
         */
        AtomicInteger atomicInteger = new AtomicInteger(Integer.MAX_VALUE);
        System.out.println(atomicInteger); //2147483647
        int i1 = atomicInteger.incrementAndGet();
        System.out.println(i1); //-2147483648
        i1 = atomicInteger.incrementAndGet();
        System.out.println(i1); //-2147483647
    }
}
