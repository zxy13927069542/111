package com.ying.tjava.network.udp;

import java.net.SocketException;

import org.junit.jupiter.api.Test;

public class TestClient {

	@Test
	public void testClient() {
		try {
			Client c = new Client(11211);
			c.start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
