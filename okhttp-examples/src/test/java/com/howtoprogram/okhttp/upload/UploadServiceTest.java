package com.howtoprogram.okhttp.upload;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class UploadServiceTest {

	@Test
	public void testUploadImage() throws IOException {
		File file = new File("d:\\Tmp\\abc.png");
		UploadService uploadService = new UploadService();
		uploadService.uploadImage(file, "abc.png");

	}

}
