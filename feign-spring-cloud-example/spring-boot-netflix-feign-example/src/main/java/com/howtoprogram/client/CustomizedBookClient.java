package com.howtoprogram.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howtoprogram.services.Book;

@FeignClient(name = "book-client", url = "http://localhost:8080/v1/books",
  fallback = BookClientFallback.class,
  configuration = FeignBookConfiguration.class)
public interface CustomizedBookClient {

  @RequestMapping(value = "/{id}")
  Book findBookById(@PathVariable("id") Long id);

  @RequestMapping(method = RequestMethod.POST)
  Book addBook(Book book);

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  Book updateBook(@PathVariable("id") Long id, Book book);

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  void deleteBook(@PathVariable("id") Long id);
}
