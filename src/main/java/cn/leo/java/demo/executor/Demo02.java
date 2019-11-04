package cn.leo.java.demo.executor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo02 {
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

		pool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("now time: " + new Date());
			}
		}, 10, TimeUnit.SECONDS);

	}
}
