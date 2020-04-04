package cn.leo.java.demo.gc.stack;

/**
 * -XX:+PrintGCDetails -Xss500m
 * -Xss50m 把每个线程栈内存设置大, 通过递归调用使用每个栈内存
 * 不停创建线程, 理论上会把主机物理内存使用光
 * @author leo
 */
public class Demo02 {

	public static void main(String[] args) throws InterruptedException {
//		List<Thread> list = new LinkedList<>();
		for (int i = 0; i < 20; i++) {
			Thread thread = new Thread() {
				@Override
				public void run() {
					a();
				}

				private void a() {
//					System.out.println(System.currentTimeMillis() + "a");
					b();
				}

				private void b() {
//					System.out.println(System.currentTimeMillis() + "b");
					a();
				}
			};
			thread.start();
		}
	}
}
