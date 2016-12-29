package com.howtoprogram.repository.feign.upload;

import feign.Feign;
import feign.Response;
import feign.form.FormEncoder;
import feign.jackson.JacksonEncoder;

import java.io.File;

public class UploadService {
    private static final String HTTP_FILE_UPLOAD_URL = "http://localhost:8080/v1";


    public boolean uploadFile(String fileName, File file) {

        FileUploadResource fileUploadResource = Feign.builder()
                .encoder(new FormEncoder(new JacksonEncoder()))
                .target(FileUploadResource.class, HTTP_FILE_UPLOAD_URL);

        Response response = fileUploadResource.uploadFile("Example file", file);
        return response.status() == 200;
    }

}
