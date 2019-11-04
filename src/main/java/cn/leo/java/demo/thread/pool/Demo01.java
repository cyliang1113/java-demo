package cn.leo.java.demo.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo01 {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(1);

		Future<String> future = pool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("run...");
				//Thread.sleep(5000);
				return "done";
			}

		});

		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
