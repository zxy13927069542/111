package com.ying.tjava.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.junit.jupiter.api.Test;

public class OldClient {

	@Test
	public void testGet() throws IOException {
		getBaidu();
	}
	
	public void getBaidu() throws IOException {
		URL url = new URL("https://www.baidu.com");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.setConnectTimeout(5000); // 请求超时5秒
		// 设置HTTP头:
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 11; Windows NT 5.1)");
		// 连接并发送HTTP请求:
		conn.connect();
		// 判断HTTP响应是否200:
		if (conn.getResponseCode() != 200) {
		    throw new RuntimeException("bad response");
		}		
		// 获取所有响应Header:
		Map<String, List<String>> map = conn.getHeaderFields();
		for (String key : map.keySet()) {
		    System.out.println(key + ": " + map.get(key));
		}
		// 获取响应内容:
		InputStream input = conn.getInputStream();
//		InputStreamReader r = new InputStreamReader(input);
//		BufferedReader r1 = new BufferedReader(r);
		input.transferTo(System.out);
		
	}
}
