package cn.leo.java.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client2 {
	public static String host = "192.168.216.129";
//	public static String host = "127.0.0.1";

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			new Thread(new myRun(i)).start();
		}
	}


	private static class myRun implements Runnable {
		public myRun(int no) {
			this.no = no;
		}

		private int no;
		@Override
		public void run() {
			Socket socket = null;
			OutputStream outputStream = null;
			try {
				socket = new Socket(host, 8899); /* 当client执行完这一步时, server已经执行了inputStream.read(buf),
												              如果这时client停在这里, 那么server一直都在read数据,
												              表面上看server线程像是线程被阻塞了, 其实不是的, 它是代码一直在执行read操作,
												              和正常说的线程阻塞不是一个状态, server线程没有被阻塞, 只是代码一直在执行read操作*/

				InputStream inputStream = socket.getInputStream();
				byte[] bb = new byte[4];
				int read = inputStream.read(bb);
				int int1=bb[0]&0xff;
				int int2=(bb[1]&0xff)<<8;
				int int3=(bb[2]&0xff)<<16;
				int int4=(bb[3]&0xff)<<24;
				int i = int1 | int2 | int3 | int4;

				while (true) {
					Thread.sleep(10000);

					if (i % 10 == 0) {
						System.out.println("fd " + i + " close");
						return;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


}
