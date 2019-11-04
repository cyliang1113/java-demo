package cn.leo.java.demo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelReadDemo {
	public static void main(String[] args) {
		FileInputStream fis = null;

		try {
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			fis = new FileInputStream("D:\\knn.txt");

			FileChannel channel = fis.getChannel();

			buffer.clear();
			channel.read(buffer);

			buffer.flip();

			for (int i = buffer.position(); i < buffer.limit(); i++) {
				System.out.print((char) buffer.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
