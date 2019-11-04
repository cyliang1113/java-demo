package cn.leo.java.demo.thread;

import cn.leo.java.demo.clazz.extend.Person;

public class Demo01 {
	public static void main(String[] args) {
		final String str = "tom";
		final Person person = new Person();
		person.age = 10;
		
		new Thread(){
			@Override
			public void run() {
				System.out.println(person.age);
			}
		}.start();
	}
}
