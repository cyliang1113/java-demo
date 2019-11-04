package cn.leo.java.demo.clazz.extend;

public class Demo {
	public static void main(String[] args) {
		Man m = new Man();
		System.out.println(m.age);
		System.out.println(m.getAge());
		m.getClassName();
		

		Person p = m;
		System.out.println(p.age);
		System.out.println(p.getAge());
		p.getClassName();
		
		Gay gay = new Gay();
		gay.isPerson();
		

		// System.out.println("------------------");
		// new Man("gg");
	}
}
