package com.howtoprogram.repository.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howtoprogram.domain.Book;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BookRepositoryImplOkHttp {
	public static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

	private static final String URI_BOOK = "http://localhost:8080/v1/books";

	public void deleteBook(Long id) throws Exception {
		OkHttpClient httpclient = new OkHttpClient();
		Request request = new Request.Builder().url(URI_BOOK + "/" + id).delete().build();
		try (Response response = httpclient.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new RuntimeException("Failed to delete book with id:" + id);

			}
		}
	}

	public Book updateBook(Book book) throws Exception {

		Book updatedBook = null;
		OkHttpClient httpclient = new OkHttpClient();
		ObjectMapper mapper = new ObjectMapper();
		String jsonBook = mapper.writeValueAsString(book);
		Request request = new Request.Builder().url(URI_BOOK + "/" + book.getId())
				.put(RequestBody.create(MediaTypeJSON, jsonBook)).build();
		try (Response response = httpclient.newCall(request).execute()) {
			if (response.isSuccessful()) {
				updatedBook = mapper.readValue(response.body().bytes(), Book.class);

			}
		}
		return updatedBook;
	}

	public Book createBook(Book book) throws Exception {
		Book createdBook = null;
		OkHttpClient httpclient = new OkHttpClient();
		ObjectMapper mapper = new ObjectMapper();
		String jsonBook = mapper.writeValueAsString(book);
		Request request = new Request.Builder().url(URI_BOOK).post(RequestBody.create(MediaTypeJSON, jsonBook)).build();
		try (Response response = httpclient.newCall(request).execute()) {

			if (response.isSuccessful()) {
				// Get back the created book
				createdBook = mapper.readValue(response.body().bytes(), Book.class);

			}
		}

		return createdBook;
	}

	public Book[] getAllBooks() throws Exception {
		Book[] books = null;
		OkHttpClient httpclient = new OkHttpClient();
		Request request = new Request.Builder().url(URI_BOOK).get().build();
		try (Response response = httpclient.newCall(request).execute()) {
			ObjectMapper mapper = new ObjectMapper();
			books = mapper.readValue(response.body().bytes(), Book[].class);
		}
		return books;
	}

	public static void main(String[] args) throws Exception {
		BookRepositoryImplOkHttp bookRepository = new BookRepositoryImplOkHttp();
		// Getting the first book from the RESTful service
		Book book = bookRepository.getAllBooks()[0];
		bookRepository.deleteBook(book.getId());

	}

	public Book findBookById(Long id) {
		return null;
	}

}
