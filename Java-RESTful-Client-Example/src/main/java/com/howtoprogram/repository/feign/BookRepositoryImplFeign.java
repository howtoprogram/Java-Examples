package com.howtoprogram.repository.feign;

import java.util.List;

import com.howtoprogram.domain.Book;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class BookRepositoryImplFeign {
  private static final String URI_BOOK = "http://localhost:8080";

  public Book updateBook(Book book) throws Exception {
    BookResourceFeign bookResource = Feign.builder().encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder()).target(BookResourceFeign.class, URI_BOOK);
    Book updatedBook = bookResource.updateBook(book.getId(), book);
    return updatedBook;
  }


  public Book createBook(Book book) throws Exception {
    BookResourceFeign bookResource = Feign.builder().encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder()).target(BookResourceFeign.class, URI_BOOK);
    Book createdBook = bookResource.createBook(book);
    return createdBook;

  }

  public List<Book> getAllBooks() throws Exception {
    BookResourceFeign bookResource = Feign.builder().encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder()).target(BookResourceFeign.class, URI_BOOK);
    return bookResource.getAllBooks();

  }

  public static void main(String[] args) throws Exception {
    BookRepositoryImplFeign bookRepository = new BookRepositoryImplFeign();
    Book book = bookRepository.getAllBooks().get(0);
    bookRepository.deleteBook(book.getId());
  }



  public void deleteBook(Long id) {
    BookResourceFeign bookResource = Feign.builder().encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder()).target(BookResourceFeign.class, URI_BOOK);
    bookResource.deleteBook(id);
  }



  public Book findBookById(Long id) {
    return null;
  }

}
