package cn.leo.java.demo.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
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

			SelectionKey serverSocketChannelSelectionKey =
					serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //serverSocketChannel注册到selector选择器中
			// register()有个重载方法(如下), 可以传递一个附件att, 后面可以从selectionKey中取出来
			// netty中就是用了该方法, 把netty的AbstractNioChannel放在附件att中
			//SelectionKey serverSocketChannelSelectionKey = serverSocketChannel
			//		.register(selector, SelectionKey.OP_ACCEPT, serverSocketChannel);

			while (true) {
				selector.select(); //获取注册在selector中的socket的事件, 如: accept, read

				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();

					int ops = selectionKey.readyOps();
					System.out.println("selectionKey readyOps=" + ops);
					if (selectionKey.isAcceptable()) {
						System.out.println("select accept 事件");
						ServerSocketChannel ssChannel = (ServerSocketChannel) selectionKey.channel();
						SocketChannel socketChannel = ssChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ); //socketChannel注册到selector选择器中
					} else if (selectionKey.isReadable()) {
						System.out.println("select read 事件");
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						ByteBuffer bb = ByteBuffer.allocate(50);
						while (true) {
							int read = socketChannel.read(bb);
							if (read < 0) {
								socketChannel.close();
								break;
							} else if (read == 0) {
								break;
							}
							bb.flip();
							CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
							System.out.println("from client: " + decoder.decode(bb).toString());
						}
					} else {
						System.out.println("select other 事件");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (selector != null) {
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (serverSocketChannel != null) {
				try {
					serverSocketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
