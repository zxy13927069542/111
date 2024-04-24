package com.ying.tjava.network.http;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    private int port = 8080;

    @Test
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

    /**
     * GET / HTTP/1.1
     * Host: www.sina.com.cn
     * User-Agent: Mozilla/5.0 xxx
     * <p>
     * HTTP/1.1 200 OK
     * Content-Type: text/html
     * Content-Length: 21932
     * Content-Encoding: gzip
     */
    public void handle(InputStream in, OutputStream out) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));

        var requestOk = false;
        try {
            String header = reader.readLine();
            if (header.startsWith("GET / HTTP/1.1")) requestOk = true;
            System.out.println(header);

            while (true) {
                header = reader.readLine();
                if (header.isEmpty()) {
                    break;
                }
                System.out.println(header);
            }

            System.out.println(requestOk ? "OK" : "ERROR");
            if (requestOk) {
                String body = "<html><body><h1>Hello, world!</h1></body></html>";
                writer.write("HTTP/1.1 200 OK\r\n");
                writer.write("Content-Type: text/html\r\n");
                writer.write(String.format("Content-Length: %d\r\n", body.length()));
                writer.write("\r\n");
                writer.write(body);
                writer.flush();
            } else {
                writer.write("HTTP/1.1 400 Bad Request\r\n");
                writer.write("Content-Type: text/html\r\n");
                writer.write("\r\n");
                writer.write("<h1>Bad Request</h1>");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
