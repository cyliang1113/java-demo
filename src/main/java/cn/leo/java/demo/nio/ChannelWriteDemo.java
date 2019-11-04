package cn.leo.java.demo.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelWriteDemo {
	public static void main(String[] args) {
		FileOutputStream fos = null;

		try {
			ByteBuffer buffer = ByteBuffer.allocate(1024);

			fos = new FileOutputStream("D:\\ChannelWriteDemo.txt");

			FileChannel channel = fos.getChannel();

			buffer.clear();

			buffer.put("hello nio channell write.".getBytes());

			buffer.flip();

			channel.write(buffer);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
