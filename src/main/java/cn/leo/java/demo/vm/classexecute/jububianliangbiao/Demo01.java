package cn.leo.java.demo.vm.classexecute.jububianliangbiao;




/**
 * 栈帧中的局部变量表
 * -XX:+PrintGCDetails
 */
public class Demo01 {
//    public static void main(String[] args) {
//        int c = 64 * 1024 * 1024;
//        byte[] bytes = new byte[c];
//        System.gc(); //bytes没有被回收
//    }

//    public static void main(String[] args) {
//        int c = 64 * 1024 * 1024;
//        {
//            byte[] bytes = new byte[c];
//        }
//        System.gc(); //bytes没有被回收, 如果经过即时编译就可以被回收
//    }

    public static void main(String[] args) {
        int c = 64 * 1024 * 1024;
        {
            byte[] bytes = new byte[c];
        }
        int a = 0; //超过bytes作用域, 局部变量表里bytes和a是重用的, 是同一个槽
                   //a=0时, byte数组对象变成不可达, 等效于bytes=null
        System.gc(); //bytes被回收,
    }

//    public static void main(String[] args) {
//        int c = 64 * 1024 * 1024;
//
//        byte[] bytes = new byte[c];
//        bytes = null;
//
//        System.gc(); //bytes被回收
//    }

}
