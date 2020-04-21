package cn.leo.java.demo.vm.classexecute.demo05;

public class Op {
    public void operateAndPrint(Operate operate, int a, int b) {
        int result = operate.operate(a, b);
        System.out.println(result);
    }
    public static int add(int a, int b) {
        return a + b;
    }
}
