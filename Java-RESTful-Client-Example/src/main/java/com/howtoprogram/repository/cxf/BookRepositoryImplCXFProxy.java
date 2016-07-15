package com.howtoprogram.repository.cxf;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.howtoprogram.domain.Book;

public class BookRepositoryImplCXFProxy {
  private static final String URI_BOOK = "http://localhost:8080/v1/books";

  public Book createBook(Book book) throws Exception {
    BookResource proxy = JAXRSClientFactory.create(URI_BOOK, BookResource.class,
        Collections.singletonList(new JacksonJsonProvider()));
    WebClient.client(proxy).type(MediaType.APPLICATION_JSON_TYPE);
    WebClient.client(proxy).accept(MediaType.APPLICATION_JSON_TYPE);
    Book createdBook = proxy.createBook(book);
    return createdBook;

  }

  public void deleteBook(Long id) {
    BookResource proxy = JAXRSClientFactory.create(URI_BOOK, BookResource.class);
    WebClient.client(proxy).accept(MediaType.APPLICATION_JSON_TYPE);
    proxy.deleteBook(id);
  }

  public Book updateBook(Book book) throws Exception {
    BookResource proxy = JAXRSClientFactory.create(URI_BOOK, BookResource.class,
        Collections.singletonList(new JacksonJsonProvider()));
    WebClient.client(proxy).accept(MediaType.APPLICATION_JSON_TYPE);
    WebClient.client(proxy).type(MediaType.APPLICATION_JSON_TYPE);
    Book updatedBook = proxy.updateBook(book.getId(), book);
    return updatedBook;
  }

  public List<Book> getAllBooks() throws Exception {
    BookResource proxy = JAXRSClientFactory.create(URI_BOOK, BookResource.class,
        Collections.singletonList(new JacksonJsonProvider()));
    WebClient.client(proxy).accept(MediaType.APPLICATION_JSON_TYPE);
    return proxy.getAllBooks();

  }


  public static void main(String[] args) throws Exception {
    BookRepositoryImplCXFProxy bookRepository = new BookRepositoryImplCXFProxy();
    Book book = new Book(null, "Effective Java", "Joshua Bloch");
    Book createdBook = bookRepository.createBook(book);
    System.out.println(createdBook);
  }



  public Book findBookById(Long id) {
    return null;
  }

}
