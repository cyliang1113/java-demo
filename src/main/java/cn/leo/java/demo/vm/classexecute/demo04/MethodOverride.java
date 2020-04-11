package cn.leo.java.demo.vm.classexecute.demo04;

public class MethodOverride {
    public static class Person {
        public void print() {
            System.out.println("person");
        }
    }

    public static class Man extends Person {
        public void print() {
            System.out.println("man");
        }
    }
}
