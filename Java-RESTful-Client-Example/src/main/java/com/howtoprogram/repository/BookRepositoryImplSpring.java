package com.howtoprogram.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.howtoprogram.domain.Book;

public class BookRepositoryImplSpring {

  private static final String URI_BOOK = "http://localhost:8080/v1/books";

  private RestTemplate restTemplate = new RestTemplate();

  public void deleteBook(Long id) {
    restTemplate.delete(URI_BOOK + "/{id}", id);
  }

  public void updateBook(Book book) {
    restTemplate.put(URI_BOOK + "/{id}", book, book.getId());
  }


  public static void main(String[] args) {
    BookRepositoryImplSpring repository = new BookRepositoryImplSpring();
    // Getting the first book from the RESTful service
    Book book = repository.getAllBooks()[0];
    // Try to delete the book
    repository.deleteBook(book.getId());
  }

  public Book createBook(Book book) {
    // Book createdBook = restTemplate.postForObject(URI_BOOK, book, Book.class);
    ResponseEntity<Book> responseEntity = restTemplate.postForEntity(URI_BOOK, book, Book.class);
    Book createdBook = null;
    if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
      createdBook = responseEntity.getBody();
    }
    return createdBook;
  }



  public Book[] getAllBooks() {
    Book[] books = restTemplate.getForObject(URI_BOOK, Book[].class);
    return books;
  }

  public Book findBookById(Long id) {
    return null;
  }



}
