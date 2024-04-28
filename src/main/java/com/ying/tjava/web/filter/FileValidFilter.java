package com.ying.tjava.web.filter;

import com.ying.tjava.web.Utils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebFilter("/upload/*")
public class FileValidFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String digest = req.getHeader("Signature-Method");
        String signature = req.getHeader("Signature");
            PrintWriter writer = response.getWriter();
        if (digest == null || digest.isEmpty() || signature == null || signature.isEmpty()) {
            var body = """
                    {
                        "code": "400",
                        "message": "摘要算法或签名为空"
                    }""";
            writer.write(body);
            writer.flush();
            return;
        }

        //  摘要计算
        MessageDigest messageDigest = getMessageDigest(digest);
        byte[] data = Utils.read(req);
        String sign = byteToHex(messageDigest.digest(data));
        if (signature.toUpperCase().equals(sign)) {
            request.setAttribute("file", data);
            chain.doFilter(request, response);
        } else {
            var body = """
                    {
                        "code": "400",
                        "message": "摘要无效"
                    }""";
            writer.write(body);
            writer.flush();
        }
    }



    public static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString().trim(); // 去除尾部空格
    }


    // 根据名称创建MessageDigest:
    private MessageDigest getMessageDigest(String name) throws ServletException {
        try {
            return MessageDigest.getInstance(name);
        } catch (NoSuchAlgorithmException e) {
            throw new ServletException(e);
        }
    }
}
