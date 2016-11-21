package com.howtoprogram.okhttp.upload;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadService {

	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

	public void uploadImage(File image, String imageName) throws IOException {

		OkHttpClient client = new OkHttpClient();
		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
		        .addFormDataPart("file", imageName, RequestBody.create(MEDIA_TYPE_PNG, image))
		        .build();

		Request request = new Request.Builder().url("http://localhost:8080/v1/upload")
		        .post(requestBody).build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Unexpected code " + response);
		}

	}

}
