package com.howtoprogram.repository.retrofit2;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.howtoprogram.domain.Book;

import junit.framework.Assert;

public class BookRepositoryImplRetrofit2Test {
	private BookRepositoryImplRetrofit2 bookRepositoryImplRetrofit2;

	@Before
	public void initialize() {
		bookRepositoryImplRetrofit2 = new BookRepositoryImplRetrofit2();
	}

	@Test
	public void testGetAllBooks() throws Exception {
		List<Book> books = bookRepositoryImplRetrofit2.getAllBooks();
		Assert.assertTrue(books.size() > 0);

	}

	@Test
	public void testAddBook() throws Exception {
		Book book = new Book(null, "Hell", "aaa");
		Book books = bookRepositoryImplRetrofit2.createBook(book);
		Assert.assertTrue(book != null);

	}

	@Test
	public void testUpdateBook() throws Exception {
		Book book = new Book(null, "Hell", "aaa");
		Book books = bookRepositoryImplRetrofit2.createBook(book);
		Assert.assertTrue(book != null);

	}
}
