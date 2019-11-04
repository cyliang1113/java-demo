package cn.leo.java.demo.clazz.fanshe;

public class Person<T> {
	private T name;

	public T getName() {
		return name;
	}

	public void setName(T name) {
		this.name = name;
	}

}
