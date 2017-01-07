package com.howtoprogram.okhttp.cache;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CacheOkHttpExample {

	private final OkHttpClient client;

	public CacheOkHttpExample(File cacheDirectory) throws Exception {
		int cacheSize = 10 * 1024 * 1024; // 10 MiB
		Cache cache = new Cache(cacheDirectory, cacheSize);
		// Just to clear all previous caches on the given directory to make sure
		// all our tests run correctly
		cache.evictAll();
		client = new OkHttpClient.Builder().cache(cache).build();
	}

	public void getMasterData() throws Exception {

		// The below URL will produce the response with cache in 20 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();

		Response response1 = client.newCall(request).execute();
		if (!response1.isSuccessful()) {
			throw new IOException("Unexpected code " + response1);
		}

		String response1Body = response1.body().string();
		System.out.println("Res1 response:        " + response1);
		System.out.println("Res1 cache response:  " + response1.cacheResponse());
		System.out.println("Res1 network response:" + response1.networkResponse());

		Response response2 = client.newCall(request).execute();
		if (!response2.isSuccessful()) {
			throw new IOException("Unexpected code " + response2);
		}

		String response2Body = response2.body().string();
		System.out.println("Res2 response:         " + response2);
		System.out.println("Res2 cache response:   " + response2.cacheResponse());
		System.out.println("Res2 network response: " + response2.networkResponse());
		System.out.println("Res2 equals Res1? " + response1Body.equals(response2Body));

		// To make sure the previous cached was stale
		Thread.sleep(21000);

		Response response3 = client.newCall(request).execute();
		if (!response3.isSuccessful()) {
			throw new IOException("Unexpected code " + response3);
		}
		String response3Body = response3.body().string();
		System.out.println("Res3 response:        " + response3);
		System.out.println("Res3 cache response:  " + response3.cacheResponse());
		System.out.println("Res3 network response:" + response3.networkResponse());

	}

	public void getLocationMaterData() throws Exception {
		// The below URL will produce the response with cache in 20 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();

		Response response4 = client.newCall(request).execute();
		if (!response4.isSuccessful()) {
			throw new IOException("Unexpected code " + response4);
		}
		String response4Body = response4.body().string();
		System.out.println("Res4 response:        " + response4);
		System.out.println("Res4 cache response:  " + response4.cacheResponse());
		System.out.println("Res4 network response:" + response4.networkResponse());

		request = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build())
		        .url("http://httpbin.org/cache/20").build();

		Response response5 = client.newCall(request).execute();
		if (!response5.isSuccessful()) {
			throw new IOException("Unexpected code " + response5);
		}
		String response5Body = response5.body().string();
		System.out.println("Res5 response:         " + response5);
		System.out.println("Res5 cache response:   " + response5.cacheResponse());
		System.out.println("Res5 network response: " + response5.networkResponse());
	}

	public void getOperatorMaterData() throws Exception {
		// The below URL will produce the response with cache in 5 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();

		Response response6 = client.newCall(request).execute();
		if (!response6.isSuccessful()) {
			throw new IOException("Unexpected code " + response6);
		}
		String response6Body = response6.body().string();
		System.out.println("Res6 response:        " + response6);
		System.out.println("Res6 cache response:  " + response6.cacheResponse());
		System.out.println("Res6 network response:" + response6.networkResponse());

		// Only accept the response if it is in the cache. If the response isn't
		// cached, a 504 Unsatisfiable Request response will be returned.

		request = new Request.Builder()
		        .cacheControl(new CacheControl.Builder().onlyIfCached().build())
		        .url("http://httpbin.org/cache/20").build();

		Response response7 = client.newCall(request).execute();

		String response7Body = response7.body().string();
		System.out.println("Res7 response:         " + response7);
		System.out.println("Res7 cache response:   " + response7.cacheResponse());
		System.out.println("Res7 network response: " + response7.networkResponse());

		// Sleep 20 seconds to make sure the cache will be stale. Then the 504
		// error will be thrown.
		Thread.sleep(20000);

		Response response8 = client.newCall(request).execute();

		String response8Body = response8.body().string();
		System.out.println("Res8 response:         " + response8);
		System.out.println("Res8 cache response:   " + response8.cacheResponse());
		System.out.println("Res8 network response: " + response8.networkResponse());

	}

	public void getZoneMaterData() throws Exception {
		// The below URL will produce the response with cache in 20 seconds
		Request request = new Request.Builder().url("http://httpbin.org/cache/20").build();

		Response response9 = client.newCall(request).execute();
		if (!response9.isSuccessful()) {
			throw new IOException("Unexpected code " + response9);
		}
		String response9Body = response9.body().string();
		System.out.println("Res9 response:        " + response9);
		System.out.println("Res9 cache response:  " + response9.cacheResponse());
		System.out.println("Res9 network response:" + response9.networkResponse());

		// Sleep 22 seconds to make sure the cache will be stale.
		Thread.sleep(22000);
		request = new Request.Builder()
		        .cacheControl(new CacheControl.Builder().onlyIfCached().build())
		        .url("http://httpbin.org/cache/20").build();

		Response response10 = client.newCall(request).execute();

		String response10Body = response10.body().string();
		System.out.println("Res10 response:         " + response10);

		request = new Request.Builder()
		        .cacheControl(new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build())
		        .url("http://httpbin.org/cache/20").build();

		Response response11 = client.newCall(request).execute();
		String response11Body = response11.body().string();
		System.out.println("Res11 response:        " + response11);
		System.out.println("Res11 cache response:  " + response11.cacheResponse());
		System.out.println("Res11 network response:" + response11.networkResponse());

	}

	public static void main(String args[]) throws Exception {
		File cacheLoc = new File("/var/tmp/okhttp");
		CacheOkHttpExample cacheOkHttp = new CacheOkHttpExample(cacheLoc);
		cacheOkHttp.getLocationMaterData();
		// cacheOkHttp.getOperatorMaterData();
		// cacheOkHttp.getZoneMaterData();
	}
}
