package cn.leo.java.demo.vm.classexecute.demo04;


/**
 * 栈帧中方法调用
 */
public class Demo04 {
    public static void main(String[] args) {
        MethodOverride.Person person = new MethodOverride.Person();
        MethodOverride.Person person1 = new MethodOverride.Man();
        person.print();
        person1.print();
    }
}
