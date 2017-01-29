package com.howtoprogram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.howtoprogram.client.BookClient;
import com.howtoprogram.services.Book;

import feign.FeignException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
  @Autowired
  private BookClient bookClient;

  @Test
  public void testFindBookById() {
    // add a book
    Book book = new Book(null, "Java Programming", "Joyce Farrell");
    book = bookClient.addBook(book);
    // find book by id
    Book foundBook = bookClient.findBookById(book.getId());
    assertEquals(book.getId(), foundBook.getId());
  }

  @Test
  public void testAddBook() {
    Book book = new Book(null, "Reactive Java Programming", "Andrea Maglie");
    book = bookClient.addBook(book);
    assertNotNull(book.getId());
  }

  @Test
  public void testUpdateBook() {
    String pythonBook = "Learning Python";
    Book book = new Book(null, pythonBook, "Mark Lutz");
    book = bookClient.addBook(book);

    pythonBook = pythonBook + ", 5th Edition";
    book.setName(pythonBook);
    Book updatedBook = bookClient.updateBook(book.getId(), book);

    assertEquals(pythonBook, updatedBook.getName());

  }

  @Test
  public void testDeleteBook() {
    // create a book
    Book book = new Book(null, "C++ For Dummies", "Stephen R. Davis");
    book = bookClient.addBook(book);

    // delete book
    bookClient.deleteBook(book.getId());
    try {
      book = bookClient.findBookById(book.getId());
      fail("Failed to delete the book");
    } catch (Exception e) {
      e.printStackTrace();
      FeignException fe = (FeignException) e.getCause();
      assertEquals(404, fe.status());
    }
  }
}
