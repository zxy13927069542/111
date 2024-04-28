package com.ying.tjava.web;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Utils {
    public static byte[] read(HttpServletRequest req) throws IOException {
        ServletInputStream in = req.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        while (true) {
            int n = in.read(buff);
            if (n == -1) {
                break;
            }
            out.write(buff, 0, n);
        }
        return out.toByteArray();
    }
}
