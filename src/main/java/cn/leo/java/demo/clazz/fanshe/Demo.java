package cn.leo.java.demo.clazz.fanshe;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Demo {
	public static void main(String[] args) {
		Class<String> clazz = String.class;
		System.out.println(clazz instanceof Type);

		Type[] genericInterfaces = clazz.getGenericInterfaces();
		System.out.println(genericInterfaces.length);
		System.out.println(genericInterfaces[0]);
		System.out.println(genericInterfaces[1]);
		System.out.println(genericInterfaces[2]);
		
		System.out.println(genericInterfaces[0].getClass());
		System.out.println(genericInterfaces[1].getClass());
		System.out.println(genericInterfaces[2].getClass());
		
		Type[] actualTypeArguments = ((ParameterizedType)genericInterfaces[1]).getActualTypeArguments();
		for (Type type : actualTypeArguments) {
			System.out.println(type);
		}
	}
}
