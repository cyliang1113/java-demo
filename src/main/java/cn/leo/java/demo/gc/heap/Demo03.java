package cn.leo.java.demo.gc.heap;

/**
 * java8 常量池在堆中
 * @author leo
 */
public class Demo03 {

	public static void main(String[] args) throws InterruptedException {
		Integer a1 = 1;
		Integer a2 = 1;
		System.out.println("a1 == a2: " + (a1 == a2));

		Integer b1 = 127;
		Integer b2 = 127;
		System.out.println("b1 == b2: " + (b1 == b2));

		Integer c1 = 128;
		Integer c2 = 128;
		System.out.println("c1 == c2: " + (c1 == c2));
	}
}
