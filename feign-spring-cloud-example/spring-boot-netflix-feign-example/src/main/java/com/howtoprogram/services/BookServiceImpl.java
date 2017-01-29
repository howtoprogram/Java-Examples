package com.howtoprogram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtoprogram.client.BookClient;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  private BookClient bookClient;

  @Override
  public Book findBookById(Long id) {
    return bookClient.findBookById(id);
  }

  @Override
  public Book addBook(Book book) {
    return bookClient.addBook(book);
  }

  @Override
  public Book updateBook(Book book) {
    return bookClient.updateBook(null, book);
  }

  @Override
  public void deleteBook(Long id) {
    bookClient.deleteBook(id);

  }
}
