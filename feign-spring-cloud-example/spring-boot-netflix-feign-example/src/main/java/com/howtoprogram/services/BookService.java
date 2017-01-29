package com.howtoprogram.services;

public interface BookService {

  Book findBookById(Long id);

  Book addBook(Book book);

  Book updateBook(Book book);

  void deleteBook(Long id);
}
