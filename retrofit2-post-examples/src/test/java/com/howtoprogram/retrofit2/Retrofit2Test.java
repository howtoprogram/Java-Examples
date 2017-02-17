package com.howtoprogram.retrofit2;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class Retrofit2Test {

    @Test
    public void testPostObject() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);
        Book book = new Book(1l, "Java How To Program", "Paul Deitel");

        Call<Book> call = bookResource.addBook(book);
        Response<Book> response = call.execute();

        assertTrue(response.isSuccessful());

    }

    @Test
    public void testPostJson() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);
        Book book = new Book(1l, "Java How To Program", "Paul Deitel");

        Call<Book> call = bookResource.addBook(book);
        Response<Book> response = call.execute();

        assertTrue(response.isSuccessful());

    }

    @Test
    public void testPostForm() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);

        Call<Book> call = service.updateBook(1l, "Tom1", "Riddle1");

        Response<Book> response = call.execute();
        assertTrue(response.isSuccessful());


    }
    @Test
    public void testPostFormWithFieldMap() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("author","Joshua Bloch");
        fieldsMap.put("name","Java puzzlers");
        fieldsMap.put("id","1");
        Call<Book> call = service.updateBook(fieldsMap);

        Response<Book> response = call.execute();
        assertTrue(response.isSuccessful());

    }

    @Test
    public void testPostMultipart() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);

        // create RequestBody instance from file
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("RestImage.png").getFile());

        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), reqFile);

        // add book id part within the multipart request
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1l));

        Call<Book> call = service.addBookCover(id, body);

        Response<Book> response = call.execute();
        assertTrue(response.isSuccessful());

    }

    @Test
    public void testPostMultipart2() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);

        // create RequestBody instance from file
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("RestImage.png").getFile());

        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1l));

        Call<Book> call = service.addBookCover2(id, reqFile);

        Response<Book> response = call.execute();
        assertTrue(response.isSuccessful());

    }

}
