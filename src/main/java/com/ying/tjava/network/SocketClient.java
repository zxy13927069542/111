package com.ying.tjava.network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketClient {
	public static void connect(int port) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", port);
		try (InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream()) {
			handle(in, out);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			socket.close();
			
		}
		
	}

	public static void handle(InputStream input, OutputStream output) throws IOException {
		Writer writer = new OutputStreamWriter(output,StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String msg = sc.nextLine();
			writer.write(msg);
			writer.flush();
			if (msg.equals("quit")) {
				break;
			}
		}
	}
}
