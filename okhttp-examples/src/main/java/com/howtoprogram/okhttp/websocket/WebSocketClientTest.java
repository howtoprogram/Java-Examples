package com.howtoprogram.okhttp.websocket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WebSocketClientTest {

	public static void main(String... args) {

		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder().url("ws://echo.websocket.org").build();
		EchoWebSocketListener listener = new EchoWebSocketListener();

		WebSocket ws = client.newWebSocket(request, listener);

		// Trigger shutdown of the dispatcher's executor so this process can
		// exit cleanly.
		client.dispatcher().executorService().shutdown();
	}
}
