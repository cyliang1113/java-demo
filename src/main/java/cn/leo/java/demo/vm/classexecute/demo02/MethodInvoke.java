package cn.leo.java.demo.vm.classexecute.demo02;

public class MethodInvoke {
    private void sss(int i, String s, int j, int k, Object o){
        System.out.println("sss");
    }

    public static void jjj() {
        System.out.println("jjj");
    }

    public void ggg() {
        System.out.println("ggg");
        sss(0, "000", 1, 3, null);
    }
}
