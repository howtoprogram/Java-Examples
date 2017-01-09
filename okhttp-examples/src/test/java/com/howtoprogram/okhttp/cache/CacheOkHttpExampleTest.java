package com.howtoprogram.okhttp.cache;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static junit.framework.Assert.*;

public class CacheOkHttpExampleTest {

	private OkHttpClient client;

	@Before
	public void initCache() throws IOException {
		int cacheSize = 10 * 1024 * 1024; // 10 MiB
		File cacheLoc = new File("/var/tmp/okhttp");
		Cache cache = new Cache(cacheLoc, cacheSize);
		// Clear all previous caches on the given directory to make sure
		// all our tests run correctly
		cache.evictAll();
		client = new OkHttpClient.Builder().cache(cache).build();
	}

	@Test
	public void cacheWithOkHttpTest() throws IOException {

		// The below URL will produce the response with cache in 20 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();
		Response response1 = client.newCall(request).execute();
		if (!response1.isSuccessful()) {
			throw new IOException("Unexpected code " + response1);
		}

		response1.close();
		assertNull(response1.cacheResponse());
		assertNotNull(response1.networkResponse());

		Response response2 = client.newCall(request).execute();
		if (!response2.isSuccessful()) {
			throw new IOException("Unexpected code " + response2);
		}
		response2.close();
		assertNotNull(response2.cacheResponse());
		assertNull(response2.networkResponse());
	}

	@Test
	public void forceNetworkResponseTest() throws IOException {
		// The below URL will produce the response with cache in 20 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();

		Response response4 = client.newCall(request).execute();
		if (!response4.isSuccessful()) {
			throw new IOException("Unexpected code " + response4);
		}
		response4.close();
		assertNull(response4.cacheResponse());
		assertNotNull(response4.networkResponse());

		request = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build())
		        .url("http://httpbin.org/cache/20").build();

		Response response5 = client.newCall(request).execute();
		if (!response5.isSuccessful()) {
			throw new IOException("Unexpected code " + response5);
		}
		response5.close();
		assertNull(response5.cacheResponse());
		assertNotNull(response5.networkResponse());
	}

	@Test
	public void forceCacheResponseTest() throws Exception {
		// The below URL will produce the response with cache in 5 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();

		Response response6 = client.newCall(request).execute();
		if (!response6.isSuccessful()) {
			throw new IOException("Unexpected code " + response6);
		}
		response6.close();
		assertNull(response6.cacheResponse());
		assertNotNull(response6.networkResponse());

		// Only accept the response if it is in the cache. If the response isn't
		// cached, a 504 Unsatisfiable Request response will be returned.

		request = new Request.Builder()
		        .cacheControl(new CacheControl.Builder().onlyIfCached().build())
		        .url("http://httpbin.org/cache/20").build();

		Response response7 = client.newCall(request).execute();

		response7.close();
		assertNotNull(response7.cacheResponse());
		assertNull(response7.networkResponse());

		// Sleep 20 seconds to make sure the cache will be stale. Then the 504
		// response error will be return.
		Thread.sleep(20000);

		Response response8 = client.newCall(request).execute();

		response8.close();
		assertEquals(504, response8.code());
		assertEquals("Unsatisfiable Request (only-if-cached)", response8.message());

	}

	@Test
	public void forceCacheResponseAllowStaleTest() throws Exception {
		// The below URL will produce the response with cache in 20 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();

		Response response9 = client.newCall(request).execute();
		if (!response9.isSuccessful()) {
			throw new IOException("Unexpected code " + response9);
		}
		response9.close();
		assertNull(response9.cacheResponse());
		assertNotNull(response9.networkResponse());

		// Sleep 20 seconds to make sure the cache will be stale.
		Thread.sleep(20000);
		request = new Request.Builder()
		        .cacheControl(new CacheControl.Builder().onlyIfCached().build())
		        .url("http://httpbin.org/cache/20").build();

		Response response10 = client.newCall(request).execute();
		assertEquals(504, response10.code());
		assertEquals("Unsatisfiable Request (only-if-cached)", response10.message());

		request = new Request.Builder()
		        .cacheControl(new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build())
		        .url("http://httpbin.org/cache/20").build();

		Response response11 = client.newCall(request).execute();
		response11.close();
		assertNotNull(response11.cacheResponse());
		assertNull(response11.networkResponse());

	}

}
