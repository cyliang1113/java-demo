package cn.leo.java.demo.vm.classexecute.jububianliangbiao;


import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 栈帧中的局部变量表
 * -XX:+PrintGCDetails
 */
public class Demo02 {


    public static void main(String[] args) {
        int c = 1;
        int[] ints = new int[c];
        boolean b = ints instanceof Object;
        System.out.println("ints instanceof Object: " + b);
        System.out.println(ints.getClass());
        ints[0] = 100;
        for (int item : ints) {
            System.out.println(item);
        }

    }


}
