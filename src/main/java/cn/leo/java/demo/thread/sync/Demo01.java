package cn.leo.java.demo.thread.sync;

public class Demo01 {
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();
	
	
	public void print1(){
		synchronized (lock1) {
			// 断点, 模拟线程死锁
			System.out.println("print1 lock1");
			synchronized (lock2) {
				System.out.println("print1 lock2");
			}
		}
	}
	
	public void print2(){
		synchronized (lock2) {
			// 断点, 模拟线程死锁
			System.out.println("print2 lock2");
			synchronized (lock1) {
				System.out.println("print2 lock1");
			}
		}
	}
	
	public static void main(String[] args){
		final Demo01 demo01 = new Demo01();
		new Thread(){
			@Override
			public void run() {
				demo01.print1();
			}
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				demo01.print2();
			}
		}.start();
	}
	
}
