package com.howtoprogram.repository;

import java.util.List;

import com.howtoprogram.domain.Book;

public interface BookRepository {

  List<Book> getAllBooks();

  Book createBook(Book book);

  Book updateBook(Book book);

  Book findBookById(Long id);

  void deleteBook(Long id);

}
