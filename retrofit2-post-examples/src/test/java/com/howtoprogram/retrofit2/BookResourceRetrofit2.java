package com.howtoprogram.retrofit2;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.*;

import java.util.Map;

public interface BookResourceRetrofit2 {

    @FormUrlEncoded
    @POST("post")
    Call<Book> updateBook(@FieldMap Map<String, String> fieldsMap);

    @Multipart
    @POST("post")
    Call<Book> addBookCover(@Part("id") RequestBody id, @Part MultipartBody.Part photo);


    @POST("post")
    Call<Book> addBook(@Body Book book);


    @FormUrlEncoded
    @POST("post")
    Call<Book> updateBook(@Field("id") Long id, @Field("author") String first,
                          @Field("name") String name);


    @Multipart
    @POST("post")
    Call<Book> addBookCover2(@Part("id") RequestBody id, @Part("photo") RequestBody photo);
}
