package cn.leo.java.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		InputStream inputStream = null;
		try {
			server = new ServerSocket(5209);

			while (true) {
				System.out.println("server.accept()");
				socket = server.accept(); // 代码执行到这里会停住, 是在等待client请求连接, 不是线程阻塞, 线程的状态还是正常运行的, 这叫方法阻塞可能更合理 

				System.out.println("socket.getInputStream()");
				inputStream = socket.getInputStream();

				byte[] buf = new byte[1024];
				long time = System.currentTimeMillis();
				
				System.out.println("inputStream.read(buf)");
				inputStream.read(buf); // 代码执行到这里会一直read client传过来的数据, 中间如果有消耗长时间的, 会一直在这里, 不会往下执行, 直到client数据read完
				
				System.out.println("time:" + (System.currentTimeMillis() - time));
				System.out.println(new String(buf));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
