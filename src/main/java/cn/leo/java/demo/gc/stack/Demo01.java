package cn.leo.java.demo.gc.stack;

/**
 * -Xss128k -XX:+PrintGCDetails
 * -Xss128k 设置每个线程栈的大小
 * 递归调用 把一个线程栈内存用光
 * @author leo
 */
public class Demo01 {

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		main(args);
	}
}
