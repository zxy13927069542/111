package com.ying.tjava.network;

import org.junit.jupiter.api.Test;

import com.ying.tjava.network.tcp.SocketServer;


public class SocketServerTest {

	@Test
	public void start() {
		SocketServer.listen(9966);
	}
}
