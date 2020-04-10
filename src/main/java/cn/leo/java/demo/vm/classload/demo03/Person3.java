package cn.leo.java.demo.vm.classload.demo03;

public class Person3 {
    static {
        System.out.println("class Person3 init");
    }
    public static final int value = 345; //345在类常量池中
    public static int value2 = 999; //999不在类常量池中, 类初始化时会赋值
    public final int value3 = 888; //888在类常量池中

}
