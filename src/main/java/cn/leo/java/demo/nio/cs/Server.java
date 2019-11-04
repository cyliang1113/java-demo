package cn.leo.java.demo.nio.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Date;
import java.util.Iterator;

public class Server {
	public static void main(String[] args) {
		ServerSocketChannel serverSocketChannel = null;
		SocketChannel socketChannel = null;
		Selector selector = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
			serverSocketChannel.configureBlocking(false);

			selector = Selector.open();

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				System.out.println("select()");
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
                    System.out.println("key=" + key);
                    System.out.println("key.channel()=" + key.channel().getClass());
					iterator.remove();

					if (key.isAcceptable()) {
						System.out.println("Accept----");
						ServerSocketChannel serverSocketChannel2 = (ServerSocketChannel) key.channel();
						socketChannel = serverSocketChannel2.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						System.out.println("Read----");
						socketChannel = (SocketChannel) key.channel();

						ByteBuffer bb = ByteBuffer.allocate(50);
						while(true){
							int read = socketChannel.read(bb);
							if(read <= 0){
								if(read < 0){
									socketChannel.close();
								}
								break;
							}
							bb.flip();
							CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
							System.out.println("from client: " + decoder.decode(bb).toString());
						}
						

//						CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
//
//						socketChannel.write(encoder.encode(CharBuffer.wrap("ok")));
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
