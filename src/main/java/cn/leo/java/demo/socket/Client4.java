package cn.leo.java.demo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client4 {

	public static void main(String[] args) {
		Socket socket = null;
		OutputStream outputStream = null;
//		String host = "192.168.216.129";
		String host = "127.0.0.1";

		for (int i = 0; i < 3; i++) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					Socket socket = null;
					OutputStream outputStream = null;
					try {
						socket = new Socket(host, 8899); /* 当client执行完这一步时, server已经执行了inputStream.read(buf),
												              如果这时client停在这里, 那么server一直都在read数据,
												              表面上看server线程像是线程被阻塞了, 其实不是的, 它是代码一直在执行read操作,
												              和正常说的线程阻塞不是一个状态, server线程没有被阻塞, 只是代码一直在执行read操作*/
//			Thread.sleep(10000);
						outputStream = socket.getOutputStream();
						Scanner scanner = new Scanner(System.in);

						for (; ; ) {
							String line = Thread.currentThread().getName() + Thread.currentThread().getId();
							if (line != null) {
								outputStream.write(line.getBytes());
								outputStream.flush();
							}
							Thread.sleep(2000);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
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
			}).start();


		}
	}

}
