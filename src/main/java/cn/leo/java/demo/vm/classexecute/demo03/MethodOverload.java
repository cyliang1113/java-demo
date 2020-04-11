package cn.leo.java.demo.vm.classexecute.demo03;

public class MethodOverload {
    public static class Person {
    }

    public static class Man extends Person {
    }

    public void print(Person person) {
        System.out.println("person");
    }

    public void print(Man man) {
        System.out.println("man");
    }
}
