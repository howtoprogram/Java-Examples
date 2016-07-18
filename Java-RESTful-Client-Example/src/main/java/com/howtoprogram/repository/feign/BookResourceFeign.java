package com.howtoprogram.repository.feign;

import java.util.List;

import com.howtoprogram.domain.Book;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Accept: application/json")
public interface BookResourceFeign {

  @RequestLine("GET /v1/books")
  List<Book> getAllBooks();

  @Headers("Content-Type: application/json")
  @RequestLine("POST /v1/books")
  Book createBook(Book book);

  @Headers("Content-Type: application/json")
  @RequestLine("PUT /v1/books/{id}")
  Book updateBook(@Param("id") Long id, Book book);

  @RequestLine("DELETE /v1/books/{id}")
  void deleteBook(@Param("id") Long id);

}
