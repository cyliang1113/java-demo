package cn.leo.java.demo.fanxing;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		add(list, new Integer(1));
		String string = list.get(0);
		System.out.println(string);
	}

	//List 跳过了类型检查
	public static void add(List list, Object obj) {
		list.add(obj);
	}
}
