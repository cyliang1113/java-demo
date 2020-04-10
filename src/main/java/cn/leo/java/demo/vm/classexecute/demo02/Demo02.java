package cn.leo.java.demo.vm.classexecute.demo02;


/**
 * 栈帧中方法调用
 */
public class Demo02 {
    public static void main(String[] args) {
        MethodInvoke methodInvoke = new MethodInvoke();
        methodInvoke.ggg();
        MethodInvoke.jjj();
    }
}
