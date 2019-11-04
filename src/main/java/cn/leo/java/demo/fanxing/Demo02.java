package cn.leo.java.demo.fanxing;

import java.util.ArrayList;
import java.util.List;

public class Demo02 {
	public static void main(String[] args) {
		Object[] arr = new Long[1];
		arr[0] = "111";	// 编译通过,运行报错

		//List<Object> l = new ArrayList<Long>(); // 编译报错
	}
}
