package com.ying.tjava.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Consumer;

public class Client {
	//	通过consumer注册回调函数，用于业务处理
	private Consumer<String> msgHandler;
	private DatagramSocket ds;
	
	public Client(int port) throws SocketException {
		ds = new DatagramSocket();
		ds.connect(new InetSocketAddress("127.0.0.1", port));
		ds.setSoTimeout(1000);
		msgHandler = (t) -> {
			System.out.println("Received msg:");
			System.out.println(t);
		};
	}
	
	public void start() throws SocketException {
		//	注册一个新线程用于报文接收
		Thread listen = new Thread(() -> {
			listen(ds);
		});
		listen.start();
		
		//	主线程用于报文发送
		Scanner sc = new Scanner(System.in);
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
		while (true) {
			var msg = sc.nextLine();
			if (msg.equals("quit")) {
				//	结束监听
				listen.interrupt();
				break;
			}
			
			packet.setData(msg.getBytes(StandardCharsets.UTF_8));
			try {
				ds.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ds.disconnect();
		ds.close();
	}
	
	//	监听消息
	public void listen(DatagramSocket ds) {
		DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
			System.out.println("Start client listening...");
			while (!Thread.currentThread().isInterrupted()) {
				try {
					ds.receive(p);
//					使用一个新线程对报文数据进行业务处理
					new Thread(() -> {
						msgHandler.accept(new String(
								p.getData(), p.getOffset(), p.getLength(), StandardCharsets.UTF_8));
					}).start();
				} catch (SocketTimeoutException e) {
					//	超时
					continue;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Stop client listening!");
			
	}
	
}


