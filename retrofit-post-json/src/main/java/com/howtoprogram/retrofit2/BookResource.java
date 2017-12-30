package com.howtoprogram.retrofit2;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.*;

import java.util.HashMap;
import java.util.Map;

public interface BookResource {


    @POST("books")
    Call<Book> createBook(@Body Book book);

    @POST("books")
    Call<Book> updateBook(@Body Map<String, Object> body);

}
