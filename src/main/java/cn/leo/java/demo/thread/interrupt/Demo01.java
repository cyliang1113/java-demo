package cn.leo.java.demo.thread.interrupt;

public class Demo01 {
	public static void main(String[] args) throws InterruptedException {

		Thread thread = new Thread1();

		thread.start();

		Thread.sleep(2000);
		thread.interrupt();
		Thread.sleep(2000);
	}

	static class Thread1 extends Thread {
		@Override
		public void run() {
			System.out.println("thread1 run() start");
			while (true) {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("thread1 isInterrupted");
					break;
				}
				System.out.println("thread1...");
			}
			System.out.println("thread1 run() end");
		}
	}
}
