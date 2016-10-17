package com.howtoprogram.repository.retrofit2;

import java.util.List;

import com.howtoprogram.domain.Book;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepositoryImplRetrofit2 {
	private static final String URI_BOOK = "http://localhost:8080";

	public Book updateBook(Book book) throws Exception {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(URI_BOOK).addConverterFactory(GsonConverterFactory.create())
				.build();
		BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);

		return service.updateBook(book.getId(), book).execute().body();
	}

	public Book createBook(Book book) throws Exception {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(URI_BOOK).addConverterFactory(GsonConverterFactory.create())
				.build();
		BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);
		return bookResource.createBook(book).execute().body();

	}

	public List<Book> getAllBooks() throws Exception {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(URI_BOOK).addConverterFactory(GsonConverterFactory.create())
				.build();
		BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);
		Call<List<Book>> books = bookResource.getAllBooks();
		return books.execute().body();
	}

	public static void main(String[] args) throws Exception {
		BookRepositoryImplRetrofit2 bookRepository = new BookRepositoryImplRetrofit2();
		Book book = bookRepository.getAllBooks().get(0);
		System.out.println(book);
		// bookRepository.deleteBook(book.getId());
	}

	public void deleteBook(Long id) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(URI_BOOK).addConverterFactory(GsonConverterFactory.create())
				.build();
		BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);
		bookResource.deleteBook(id);
	}

	public Book findBookById(Long id) {
		return null;
	}

}
