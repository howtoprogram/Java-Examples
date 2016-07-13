package com.howtoprogram.repository.resteasyproxy;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.howtoprogram.domain.Book;

public class BookRepositoryImplResteasyProxy {
  private static final String URI_BOOK = "http://localhost:8080/v1/books";

  public void deleteBook(Long id) {
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(URI_BOOK);
    SimpleResteasyProxyClient simpleClient = target.proxy(SimpleResteasyProxyClient.class);
    simpleClient.deleteBook(id);
  }

  public static void main(String[] args) throws Exception {
    BookRepositoryImplResteasyProxy bookRepository = new BookRepositoryImplResteasyProxy();
    Book book = bookRepository.getAllBooks().get(0);
    bookRepository.deleteBook(book.getId());
  }

  /*
   * public static void main(String[] args) throws Exception { BookRepositoryImplResteasyProxy
   * bookRepository = new BookRepositoryImplResteasyProxy(); // Getting the first book from the
   * RESTful service Book book = bookRepository.getAllBooks().get(0); System.out.println(book); //
   * Change the name book.setName(book.getName() + " 3rd"); // Then update the book book =
   * bookRepository.updateBook(book); System.out.println(book); }
   */


  /*
   * public static void main(String[] args) throws Exception { BookRepositoryImplResteasyProxy
   * bookRepository = new BookRepositoryImplResteasyProxy(); Book book = new Book(null,
   * "Effective Java", "Joshua Bloch"); Book createdBook = bookRepository.createBook(book);
   * System.out.println(createdBook); }
   */


  /*
   * public static void main(String[] args) throws Exception { BookRepositoryImplResteasyProxy
   * bookRepository = new BookRepositoryImplResteasyProxy(); List<Book> books =
   * bookRepository.getAllBooks(); System.out.println(books); }
   */
  public Book updateBook(Book book) throws Exception {

    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(URI_BOOK);
    SimpleResteasyProxyClient simpleClient = target.proxy(SimpleResteasyProxyClient.class);
    Book updatedBook = simpleClient.updateBook(book.getId(), book);
    return updatedBook;
  }


  public Book createBook(Book book) throws Exception {

    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(URI_BOOK);
    SimpleResteasyProxyClient simpleClient = target.proxy(SimpleResteasyProxyClient.class);
    Book createdBook = simpleClient.createBook(book);
    return createdBook;

  }



  public List<Book> getAllBooks() throws Exception {

    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(URI_BOOK);
    SimpleResteasyProxyClient simpleClient = target.proxy(SimpleResteasyProxyClient.class);
    return simpleClient.getAllBooks();

  }


  public Book findBookById(Long id) {
    return null;
  }

}
