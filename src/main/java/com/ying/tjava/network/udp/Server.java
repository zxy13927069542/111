package com.ying.tjava.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class Server {
	private String host;
	private int port;
	
	public Server() {}
	
	@Test
	public void testServer() throws IOException {
		System.out.println("Listening...");
		Server server = new Server();
		server.port = 11211;
		server.listen();
	}
	
	public void listen() throws IOException {
		DatagramSocket socket = new DatagramSocket(port);
		while (true) {
			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
			socket.receive(packet);
			System.out.printf("Received msg form [%s]\n", packet.getSocketAddress());
			String msg = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
			System.out.println(msg);
			packet.setData("ack".getBytes(StandardCharsets.UTF_8));
			socket.send(packet);
		}
	}


	
}
