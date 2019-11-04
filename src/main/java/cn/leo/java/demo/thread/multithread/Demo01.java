package cn.leo.java.demo.thread.multithread;

public class Demo01 {

	public static void main(String[] args) {
		Worker worker = new Worker();
		Thread mythread1 = new Thread(worker, "mythread1");
		Thread mythread2 = new Thread(worker, "mythread2");
		Thread mythread3 = new Thread(worker, "mythread3");
		mythread3.start();
		mythread1.start();
		mythread2.start();
	}

	private static class Worker implements Runnable {
		@Override
		public void run() {
			while (true) {
				printThreadMame();
			}

		}

		private synchronized void printThreadMame() {
			int i = 1000;
			while (i > 0) {
				i--;
				System.out.println(Thread.currentThread().getName());
			}
		}

	}
}
