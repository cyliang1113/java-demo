package cn.leo.java.demo.vm.classexecute.demo03;


/**
 * 栈帧中方法调用
 */
public class Demo03 {
    public static void main(String[] args) {
        MethodOverload.Person person = new MethodOverload.Person();
        MethodOverload.Person person1 = new MethodOverload.Man();
        MethodOverload methodOverload = new MethodOverload();
        methodOverload.print(person);
        methodOverload.print((MethodOverload.Man)person1);
    }
}
