package cn.leo.java.demo.vm.classexecute.demo02;

public class MethodInvoke {
    private void sss(){
        System.out.println("sss");
    }

    public static void jjj() {
        System.out.println("jjj");
    }

    public void ggg() {
        System.out.println("ggg");
        sss();
    }
}
