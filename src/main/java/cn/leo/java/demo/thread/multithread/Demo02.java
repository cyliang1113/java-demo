package cn.leo.java.demo.thread.multithread;

import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {

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
		ReentrantLock lock = new ReentrantLock(true);

		@Override
		public void run() {
			while (true) {
				printThreadMame();
			}

		}

		private void printThreadMame() {
			lock.lock();
			try {
				int i = 10;
				while (i > 0) {
					i--;
					System.out.println(Thread.currentThread().getName());
				}
			} finally {
				lock.unlock();
			}

		}

	}
}
