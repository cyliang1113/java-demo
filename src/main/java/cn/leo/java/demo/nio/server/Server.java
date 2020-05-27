package cn.leo.java.demo.nio.server;

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

public class Server {
	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel = null;
		Selector selector = null;
		try {


			serverSocketChannel = ServerSocketChannel.open(); //创建一个服务端socket
			serverSocketChannel.configureBlocking(false); //设置socket为非阻塞
			serverSocketChannel.bind(new InetSocketAddress(9999)); //绑定监听的端口

			selector = Selector.open(); //创建一个selector选择器
			SelectionKey serverSocketChannelSelectionKey = serverSocketChannel
					.register(selector, SelectionKey.OP_ACCEPT, serverSocketChannel); //服务端socket注册到selector选择器中

			while (true) {
				selector.select(); //获取注册在selector中的socket的事件, 如: accept, read

				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();

					int ops = selectionKey.readyOps();
					System.out.println("ops=" + ops);
					if (selectionKey.isAcceptable()) {
						ServerSocketChannel socket = (ServerSocketChannel) selectionKey.attachment();
						SocketChannel socketChannel = socket.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ, socketChannel);
					}

					if (selectionKey.isReadable()) {
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
