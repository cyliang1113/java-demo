package cn.leo.java.demo.string;

import cn.leo.java.demo.clazz.extend.Person;

public class Demo01 {
	public static void main(String[] args) {
		// "abc" 在常量池中创建字符串
		String a1 = "abc";
		String a2 = "abc";
		System.out.println("a1 == a2: " + (a1 == a2)); //a1 == a2: true

		// new String("abc")  在堆中创建字符串对象, 和常量池中的字符串内容一样
		String a3 = new String("abc");
		System.out.println("a1 == a3: " + (a1 == a3)); //a1 == a3: false

		// 编译时候 "a" + "b" 变成 "ab"
		String ab1 = "a" + "b";
		String ab2 = "a" + "b";
		System.out.println("ab1 == ab2: " + (ab1 == ab2)); //ab1 == ab2: true

		// 编译时候变成 StringBuilder.append()
		String ab3 = "a" + new String("b");
		System.out.println("ab1 == ab3: " + (ab1 == ab3)); //ab1 == ab3: false

		String ab4 = new String("a") + new String("b");
		System.out.println("ab1 == ab4: " + (ab1 == ab4)); //ab1 == ab4: false

		//String.intern() 在常量池创建相同的字符串并返回
		String b1 = new String("bbb");
		String b11 = b1.intern();
		String b2 = "bbb";
		System.out.println("b1 == b11: " + (b1 == b11)); //b1 == b11: false
		System.out.println("b1 == b2: " + (b1 == b2)); //b1 == b2: false
		System.out.println("b11 == b2: " + (b11 == b2)); //b11 == b2: true

		String c = "c";
		String d = "d";
		String cd1 = c + d;
		String cd2 = "cd";
		System.out.println("cd1 == cd2: " + (cd1 == cd2)); //cd1 == cd2: false

	}
}
