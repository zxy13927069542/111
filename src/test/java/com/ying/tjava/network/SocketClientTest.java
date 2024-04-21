package com.ying.tjava.network;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

import com.ying.tjava.network.tcp.SocketClient;

public class SocketClientTest {
	@Test
	public void connect() throws UnknownHostException, IOException {
		SocketClient.connect(9966);
	}
	
}
