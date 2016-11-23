package com.howtoprogram.okhttp.timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CrawlService {

	public void doCrawl(String crawlURL) throws IOException {
		
		OkHttpClient client = new OkHttpClient.Builder()
				.connectTimeout(100, TimeUnit.SECONDS)
		        .writeTimeout(10, TimeUnit.SECONDS)
		        .readTimeout(20, TimeUnit.SECONDS).build();
		
		// creates new request
		Request request = new Request.Builder()
				.url(crawlURL)
				.build();
		Response response = client.newCall(request).execute();

		if (!response.isSuccessful()) {
			throw new IOException("Unexpected code " + response);
		}
		    
	}

}
