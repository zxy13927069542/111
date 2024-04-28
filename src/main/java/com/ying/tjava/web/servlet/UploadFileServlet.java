package com.ying.tjava.web.servlet;

import com.ying.tjava.web.Utils;
import com.ying.tjava.web.filter.FileValidFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 实现文件上传
 */

@WebServlet("/upload/file")
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] data = (byte[]) req.getAttribute("file");

        String fileText = new String(data, StandardCharsets.UTF_8);
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Uploaded:</h1>");
        pw.write("<pre><code>");
        pw.write(fileText);
        pw.write("</code></pre>");
        pw.flush();
    }
}
