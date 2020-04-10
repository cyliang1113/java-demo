package cn.leo.java.demo.vm.classload.demo01;

public class Person {
    static{
        System.out.println("class Person init");
    }
    public static int value = 123;
}
