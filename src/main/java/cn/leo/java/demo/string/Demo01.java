package cn.leo.java.demo.string;

import cn.leo.java.demo.clazz.extend.Person;

public class Demo01 {
	public static void main(String[] args) {
		// "abc" 在常量池中创建字符串
		String a1 = "a";
		String a2 = "a";
		System.out.println("a1 == a2: " + (a1 == a2)); //a1 == a2: true

		// new String("abc")  在堆中创建字符串对象, 和常量池中的字符串内容一样
		String a3 = new String("a");
		System.out.println("a1 == a3: " + (a1 == a3)); //a1 == a3: false

		// 编译时候 "b" + "c" 变成 "bc"
		String bc1 = "b" + "c";
		String bc2 = "b" + "c";
		System.out.println("bc1 == bc2: " + (bc1 == bc2)); //bc1 == bc2: true

		// 编译时候变成 StringBuilder.append()
		String bc3 = "b" + new String("c");
		System.out.println("bc1 == bc3: " + (bc1 == bc3)); //bc1 == bc3: false

		//String.intern()已经在常量池中的字符串, 直接返回引用
		String d1 = "d";
		String d11 = d1.intern();
		String d2 = "d";
		System.out.println("d1 == d11: " + (d1 == d11)); //d1 == d11: true
		System.out.println("d1 == d2: " + (d1 == d2)); //d1 == d2: true
		System.out.println("d11 == d2: " + (d11 == d2)); //d11 == d2: true
		//String.intern()不在常量池中的字符串, 调用intern方法, 字符串被增加到常量池中, 并返回引用
		char[] charArrE = {'e'};
		String e1 = new String(charArrE);
		String e11 = e1.intern();
		String e2 = "e";
		System.out.println("e1 == e11: " + (e1 == e11)); //e1 == e11: true
		System.out.println("e1 == e2: " + (e1 == e2)); //e1 == e2: true
		System.out.println("e11 == e2: " + (e11 == e2)); //e11 == e2: true

	}
}
