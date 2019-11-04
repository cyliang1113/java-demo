package cn.leo.java.demo.clazz.extend;

public class Person {

	public int age = 0;

	public Person() {
		System.out.println("构造方法Person()");
		System.out.println("构造方法Person(), class:" + this.getClass());
	}

	public void getClassName() {
		System.out.println("Person");
	}

	public void isPerson() {

	}

	public int getAge(){
		return this.age;
	}

}
