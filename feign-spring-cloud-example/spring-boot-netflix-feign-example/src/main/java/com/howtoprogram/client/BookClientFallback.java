package com.howtoprogram.client;

import org.springframework.stereotype.Component;

import com.howtoprogram.services.Book;

@Component
public class BookClientFallback implements BookClient {

  @Override
  public Book findBookById(Long id) {
    return new Book();
  }

  @Override
  public Book addBook(Book book) {
    return book;
  }

  @Override
  public Book updateBook(Long id, Book book) {
    return null;
  }

  @Override
  public void deleteBook(Long id) {

  }

}
