package cn.leo.java.demo.clazz;

import java.lang.reflect.Method;

public class Demo01 {
	public static void main(String[] args) {
		Class<?> clazz = Class.class;
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println(method.getName());
		}

	}
}
