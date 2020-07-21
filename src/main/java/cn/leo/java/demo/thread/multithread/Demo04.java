package cn.leo.java.demo.thread.multithread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo04 {
	public static List<Integer> list = Arrays.asList(new Integer(0));

	public static int ssss = 0;



	public static void main(String[] args) {
		Worker worker = new Worker();
		for (int i = 0; i < 10; i++) {
			Thread mythread1 = new Thread(worker, "mythread" + i);
			mythread1.start();
		}
	}

	private static class Worker implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				synchronized (list)
				{
					int sum = list.get(0);
					sum = sum + 1;
					System.out.println(Thread.currentThread().getName() + ": " + sum);
					list.set(0, sum);
				}
//				synchronized (list)
//				{
//					ssss++;
//					System.out.println(Thread.currentThread().getName() + ": " + ssss);
//				}
			}
		}
	}
}
