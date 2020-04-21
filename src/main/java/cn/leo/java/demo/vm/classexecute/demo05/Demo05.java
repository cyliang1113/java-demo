package cn.leo.java.demo.vm.classexecute.demo05;


/**
 * lambda表达式
 * 栈帧中方法调用
 */
public class Demo05 {
    static {
        System.setProperty("jdk.internal.lambda.dumpProxyClasses", ".");
    }

    public static void main(String[] args) {
        Op op = new Op();
        op.operateAndPrint((a, b) -> a + b, 3, 2);
//        lambdaMethod.operateAndPrint((a, b) -> a + b, 3, 2);
//        lambdaMethod.operateAndPrint(LambdaMethod::add, 3, 2);
//        lambdaMethod.operateAndPrint(LambdaMethod::add, 3, 2);
    }
}
