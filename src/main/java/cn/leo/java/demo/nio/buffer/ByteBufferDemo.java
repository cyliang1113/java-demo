package cn.leo.java.demo.nio.buffer;

import java.nio.ByteBuffer;

public class ByteBufferDemo {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.clear(); // 把position设为0，把limit设为capacity

		byte a = 1;
		byte b = 2;
		byte c = 3;

		buffer.put(a);
		buffer.put(b);
		buffer.put(c);

		System.out.println("capacity: " + buffer.capacity());
		System.out.println("limit: " + buffer.limit());
		System.out.println("position: " + buffer.position());
		System.out.println("=========================");
		
		buffer.flip(); // 把limit设为当前position，把position设为0

		System.out.println("capacity: " + buffer.capacity());
		System.out.println("limit: " + buffer.limit());
		System.out.println("position: " + buffer.position());

		for (int i = buffer.position(); i < buffer.limit(); i++) {
			System.out.println(buffer.get(i));
		}

	}
}
