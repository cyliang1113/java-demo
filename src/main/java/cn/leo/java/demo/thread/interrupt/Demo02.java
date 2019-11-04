package cn.leo.java.demo.thread.interrupt;

public class Demo02 {
	public static void main(String[] args) throws InterruptedException {

		Thread thread = new Thread2();

		thread.start();

		Thread.sleep(5000);

		// 此时thread线程是在sleep中, 中断thread线程会抛出中断异常
		thread.interrupt();

	}

	static class Thread2 extends Thread {
		@Override
		public void run() {
			System.out.println("run() start");
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// 当线程sleep时, 如果有中断, 会有异常.
				System.out.println(e);
			}
			System.out.println("run() end");
		}
	}
}
