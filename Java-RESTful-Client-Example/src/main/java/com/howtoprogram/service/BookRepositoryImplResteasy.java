package com.howtoprogram.service;



import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.howtoprogram.domain.Book;

public class BookRepositoryImplResteasy {
  private static final String URI_BOOK = "http://localhost:8080/v1/books";

  public void deleteBook(Long id) {
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(URI_BOOK).path(String.valueOf(id));
    Response response = target.request().delete();
    System.out.println("Status code:" + response.getStatus());
  }


  public Book updateBook(Book book) throws Exception {
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(URI_BOOK).path(String.valueOf(book.getId()));
    Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));
    int status = response.getStatus();
    System.out.println("Status code: " + status);
    Book createdBook = response.readEntity(Book.class);
    return createdBook;
  }

  public static void main(String[] args) throws Exception {
    BookRepositoryImplResteasy bookRepository = new BookRepositoryImplResteasy();
    Book book = new Book(null, "Effective Java", "Joshua Bloch");
    Book createdBook = bookRepository.createBook(book);
    System.out.println(createdBook);
  }


  public Book createBook(Book book) throws Exception {
    ResteasyClient client = new ResteasyClientBuilder().build();
    Response response = client.target(URI_BOOK).request()
        .post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));
    int status = response.getStatus();
    System.out.println("Status code: " + status);
    Book createdBook = response.readEntity(Book.class);
    return createdBook;

  }



  public Book[] getAllBooks() throws Exception {
    ResteasyClient client = new ResteasyClientBuilder().build();
    Response response = client.target(URI_BOOK).request().get();
    int status = response.getStatus();
    System.out.println("Status code: " + status);
    Book[] books = response.readEntity(Book[].class);
    return books;
  }



  public Book findBookById(Long id) {
    return null;
  }

}
