package com.ying.tjava.network;

import org.junit.jupiter.api.Test;

public class SocketServerTest {

	@Test
	public void start() {
		SocketServer.listen(9966);
	}
}
