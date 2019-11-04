package cn.leo.java.demo.nio.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

public class Server2 {
	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel = null;
		Selector selector = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
			serverSocketChannel.configureBlocking(false);

			selector = Selector.open();

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, serverSocketChannel);

			while (true) {
				selector.select();

				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();

					int readyOps = selectionKey.readyOps();
					System.out.println("readyOps=" + readyOps);

					if ((readyOps & SelectionKey.OP_ACCEPT) != 0) {
						ServerSocketChannel socket = (ServerSocketChannel) selectionKey.attachment();
						SocketChannel socketChannel = socket.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ, socketChannel);
					}

					if ((readyOps & SelectionKey.OP_READ) != 0) {
						SocketChannel socketChannel = (SocketChannel) selectionKey.attachment();
						ByteBuffer bb = ByteBuffer.allocate(50);
						while (true) {
							int read = socketChannel.read(bb);
							if (read <= 0) {
								if (read < 0) {
									socketChannel.close();
								}
								break;
							}
							bb.flip();
							CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
							System.out.println("from client: " + decoder.decode(bb).toString());
						}
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (selector != null) {
				try {
					selector.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (serverSocketChannel != null) {
				try {
					serverSocketChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
