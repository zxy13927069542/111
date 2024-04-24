package com.ying.tjava.network.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    private int port = 8080;

    public void start() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket conn = server.accept();
                new HttpHandler(conn).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class HttpHandler extends Thread {
    private Socket conn;

    public HttpHandler(Socket conn) {
        this.conn = conn;
    }

    public void run() {
        try (InputStream in = conn.getInputStream();
             OutputStream out = conn.getOutputStream()
        ) {
            handle(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle(InputStream in, OutputStream out) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));



    }
}
