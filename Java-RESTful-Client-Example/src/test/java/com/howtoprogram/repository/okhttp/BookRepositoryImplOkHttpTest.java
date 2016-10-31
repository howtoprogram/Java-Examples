package com.howtoprogram.repository.okhttp;

import org.junit.Before;
import org.junit.Test;

import com.howtoprogram.domain.Book;

import junit.framework.Assert;

public class BookRepositoryImplOkHttpTest {
	private BookRepositoryImplOkHttp bookRepository;

	@Before
	public void initialize() {
		bookRepository = new BookRepositoryImplOkHttp();
	}

	@Test
	public void testGetAllBooks() throws Exception {
		Book[] books = bookRepository.getAllBooks();
		Assert.assertTrue(books.length > 0);

	}

	@Test
	public void testDeleteBook() throws Exception {
		Book[] books1 = bookRepository.getAllBooks();
		bookRepository.deleteBook(books1[0].getId());
		Book[] books2 = bookRepository.getAllBooks();
		Assert.assertEquals(books1.length - 1, books2.length);

	}

	@Test
	public void testAddBook() throws Exception {
		Book book = new Book(null, "Hell", "aaa");
		Book createdBook = bookRepository.createBook(book);
		Assert.assertTrue(createdBook != null && createdBook.getId() > 0);
	}

	@Test
	public void testUpdateBook() throws Exception {
		Book[] books = bookRepository.getAllBooks();
		Book book = books[0];
		book.setAuthor("Reactive Programming");
		Book updatedBook = bookRepository.updateBook(book);
		Assert.assertTrue(updatedBook != null);
		Assert.assertEquals("Reactive Programming", updatedBook.getAuthor());

	}
}
