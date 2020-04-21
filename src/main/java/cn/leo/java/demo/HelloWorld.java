package cn.leo.java.demo;

public class HelloWorld {
	public static void main(String[] args) {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setAge(helloWorld.getAge() + 111);
	}

	private void print(){}

	public static void mmm() {}

	public void ppp() {

	}

	private int age = 0;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
