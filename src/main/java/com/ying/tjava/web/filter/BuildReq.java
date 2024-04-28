package com.ying.tjava.web.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;

/**
 * HttpServletRequestWrapper 是 HttpServletRequest 的实现类
 * 通过继承该类，
 * 截取getInputStream()方法，返回自定义的输入流
 * 截取getReader()方法，返回自定义的字符流
 *
 * 可以用于在 Filter已经读取输入流的情况下，伪造一个请求，除了输入流外，其他参数与原本请求一致
 * chain.doFilter(new BuildReq(req, output.toByteArray()), response);
 *
 * 伪造Resp也同样原理
 */
public class BuildReq extends HttpServletRequestWrapper {
    private byte[] body;
    private boolean open = false;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public BuildReq(HttpServletRequest request, byte[] body) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (open) {
            throw new IllegalStateException("Cannot re-open input stream!");
        }
        open = true;
        return new ServletInputStream() {
            private int offset = 0;
            @Override
            public boolean isFinished() {
                return offset >= body.length;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                if (offset >= body.length) {
                    return -1;
                }
                int n = body[offset++] & 0xff;
                return n;
            }
        };
    }
}
