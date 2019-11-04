package cn.leo.java.demo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		OutputStream outputStream = null;
		try {
			socket = new Socket("127.0.0.1", 5209); /* 当client执行完这一步时, server已经执行了inputStream.read(buf),
												              如果这时client停在这里, 那么server一直都在read数据,
												              表面上看server线程像是线程被阻塞了, 其实不是的, 它是代码一直在执行read操作,
												              和正常说的线程阻塞不是一个状态, server线程没有被阻塞, 只是代码一直在执行read操作*/
			Thread.sleep(10000);
			outputStream = socket.getOutputStream(); 
			
			outputStream.write("hello".getBytes());
			
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
