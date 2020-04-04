package cn.leo.java.demo.gc.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * java8 字符串常量池在堆中
 * -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:-UseGCOverheadLimit
 * 
 * @author leo
 */
public class Demo02 {

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList();
		int i = 1;
		for (;;) {
			long times = System.currentTimeMillis() + i++;
//			System.out.println(times);
			String str = String.valueOf(times);
			String intern = str.intern();
			list.add(intern);
//			Thread.sleep(50);
		}
	}
}
