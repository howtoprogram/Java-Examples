package com.howtoprogram.repository.retrofit2;

import java.util.List;

import com.howtoprogram.domain.Book;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookResourceRetrofit2 {
	@Headers({ "Accept: application/json" })
	@GET("v1/books")
	Call<List<Book>> getAllBooks();

	@POST("v1/books")
	Call<Book> createBook(@Body Book book);

	@PUT("v1/books/{id}")
	Call<Book> updateBook(@Path("id") Long id, @Body Book book);

	@DELETE("v1/books/{id}")
	void deleteBook(@Path("id") Long id);

}
