package cn.leo.java.demo.nio.cs;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {

		Socket client = null;
		try {
			client = new Socket("127.0.0.1", 9999);
			OutputStream outputStream = client.getOutputStream();
			PrintWriter pw = new PrintWriter(outputStream);
			pw.write("hello");
			pw.flush();
			pw.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
