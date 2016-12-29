package com.howtoprogram.repository.feign.upload;


import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.io.File;


public interface FileUploadResource {

    @RequestLine("POST /upload")
    @Headers("Content-Type: multipart/form-data")
    Response uploadFile(@Param("name") String name, @Param("file") File file);

}