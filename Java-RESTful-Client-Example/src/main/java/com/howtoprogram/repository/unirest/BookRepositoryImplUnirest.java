package com.howtoprogram.repository.unirest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.howtoprogram.domain.Book;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

public class BookRepositoryImplUnirest {
  private static final String URI_BOOK = "http://localhost:8080/v1/books";

  public BookRepositoryImplUnirest() {
    Unirest.setObjectMapper(new ObjectMapper() {
      private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper =
          new com.fasterxml.jackson.databind.ObjectMapper();

      public <T> T readValue(String value, Class<T> valueType) {
        try {
          return jacksonObjectMapper.readValue(value, valueType);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

      public String writeValue(Object value) {
        try {
          return jacksonObjectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }

  public void deleteBook(Long id) throws Exception {
    HttpResponse<String> response =
        Unirest.delete(URI_BOOK + "/{id}").routeParam("id", String.valueOf(id)).asString();
    System.out.println("Status code:" + response.getStatus());
  }

  public static void main(String[] args) throws Exception {
    BookRepositoryImplUnirest bookRepository = new BookRepositoryImplUnirest();
    // Getting the first book from the RESTful service
    Book book = bookRepository.getAllBooks()[0];
    System.out.println(book);
    bookRepository.deleteBook(book.getId());

  }

  public Book updateBook(Book book) throws Exception {
    HttpResponse<Book> response = Unirest.put(URI_BOOK).header("accept", "application/json")
        .header("Content-Type", "application/json").body(book).asObject(Book.class);
    int status = response.getStatus();
    System.out.println("Status code: " + status);
    Book updatedBook = response.getBody();
    return updatedBook;
  }


  public Book createBook(Book book) throws Exception {
    HttpResponse<Book> response = Unirest.post(URI_BOOK).header("accept", "application/json")
        .header("Content-Type", "application/json").body(book).asObject(Book.class);
    int status = response.getStatus();
    System.out.println("Status code: " + status);
    Book createdBook = response.getBody();
    return createdBook;

  }



  public Book[] getAllBooks() throws Exception {
    HttpResponse<Book[]> response = Unirest.get(URI_BOOK).asObject(Book[].class);
    Book[] books = response.getBody();
    return books;
  }


  public Book findBookById(Long id) {
    return null;
  }

}
