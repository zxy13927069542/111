package com.ying.tjava.network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class SocketServer {
	
	@Test
	public void start() {
		SocketServer.listen(9966);
	}
	
	public static void listen(int port) {
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Listening...");
			while (true) {
				Socket conn = server.accept();
				System.out.printf("Connected from [%s]!\n", conn.getRemoteSocketAddress());
				new SocketHandler(conn).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}

class SocketHandler extends Thread {
	private Socket conn;
	
	public SocketHandler(Socket conn) {
		this.conn = conn;
	}
	
	@Override
	public void run() {
		try (InputStream input = conn.getInputStream();
				OutputStream output = conn.getOutputStream()
						) {
			handler(input, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void handler(InputStream input, OutputStream output) throws IOException {
		Writer writer = new OutputStreamWriter(output,StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		writer.write("欢迎! 这是我的服务器!\n");
		writer.flush();
		
		while(true) {
			String msg = reader.readLine();
			if (msg.equals("quit")) {
				break;
			}
			
			System.out.println(msg);
		}
		
	}
}
