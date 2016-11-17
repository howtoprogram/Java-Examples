package com.howtoprogram.okhttp.download;

import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadServiceImplOkHttp {

	public void downloadFileSync(String downloadUrl) throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(downloadUrl).build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Failed to download file: " + response);
		}
		FileOutputStream fos = new FileOutputStream("d:/tmp.txt");
		fos.write(response.body().bytes());
		fos.close();
	}

	public void downloadFileAsync(final String downloadUrl) throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(downloadUrl).build();
		client.newCall(request).enqueue(new Callback() {
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful()) {
					throw new IOException("Failed to download file: " + response);
				}
				FileOutputStream fos = new FileOutputStream("d:/tmp.txt");
				fos.write(response.body().bytes());
				fos.close();
			}
		});
	}
}
