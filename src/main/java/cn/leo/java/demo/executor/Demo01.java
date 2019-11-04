package cn.leo.java.demo.executor;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01 {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);

		pool.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("now time: " + new Date());
			}
		});
	}
}
