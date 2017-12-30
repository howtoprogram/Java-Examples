package com.howtoprogram.retrofit2;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class BookResourceTest {

    @Test
    public void testPostJsonByPojo() throws IOException {

        //create an instance of Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create an instance of the BookResource interface
        BookResource bookResource = retrofit.create(BookResource.class);
        //create a Book object
        Book book = new Book(1l, "Java How To Program", "Paul Deitel");

        // send the Book to the Rest API
        Call<Book> call = bookResource.createBook(book);
        Response<Book> response = call.execute();

        assertTrue(response.isSuccessful());

    }

    @Test
    public void testPostJsonByMap() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResource bookResource = retrofit.create(BookResource.class);
        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("name", "To Kill a Mockingbird");
        bookMap.put("author", "Harper Lee");

        Call<Book> call = bookResource.updateBook(bookMap);
        Response<Book> response = call.execute();

        assertTrue(response.isSuccessful());

    }

}
