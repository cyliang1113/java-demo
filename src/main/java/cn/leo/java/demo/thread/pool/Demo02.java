package cn.leo.java.demo.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo02 {
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

		pool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("run...");
			}
		}, 5, 10, TimeUnit.SECONDS);
	}

}
