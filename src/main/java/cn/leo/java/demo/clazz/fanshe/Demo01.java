package cn.leo.java.demo.clazz.fanshe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Demo01 {
	public static void main(String[] args) {

		List<Integer> arrayList = new ArrayList<Integer>();
		Class<? extends List> class1 = arrayList.getClass();
		System.out.println(class1);
		System.out.println(class1.getClass());
		Type[] interfaces = class1.getGenericInterfaces();
		System.out.println(interfaces.length);
		System.out.println(interfaces[0]);
		System.out.println(interfaces[0].getClass());

		Student<String> s = new Student<String>();
		Type genericSuperclass = s.getClass().getGenericSuperclass();
		System.out.println(Person.class);
		System.out.println(genericSuperclass.getClass());

	}
}
