package com.ying.tjava.network.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;

public class NewClient {
	static HttpClient client = HttpClient.newBuilder().build();
	
	@Test
	public void getBaidu() throws URISyntaxException, IOException, InterruptedException {
		//	get
		HttpRequest r = HttpRequest.newBuilder(new URI("https://www.baidu.com")).build();
		HttpResponse<String> response = client.send(r, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
	}
}
