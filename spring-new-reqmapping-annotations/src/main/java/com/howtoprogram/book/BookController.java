package com.howtoprogram.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping(value = "/books")
  public ResponseEntity<Iterable<Book>> getAllBooks() {
    return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
  }

  @PostMapping(value = "/books")
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    bookRepository.save(book);
    return new ResponseEntity<>(book, HttpStatus.CREATED);
  }

  @PutMapping(value = "/books/{id}")
  public ResponseEntity<Book> readBook(@PathVariable("id") Long id) {
    Book book = bookRepository.findOne(id);
    if (book != null) {
      return new ResponseEntity<>(book, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/books/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
    Book book = bookRepository.findOne(id);
    if (book != null) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(value = "/books/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,
    @RequestBody Book Book) {
    Book dbBook = bookRepository.findOne(id);
    if (dbBook == null) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
    dbBook.setName(Book.getName());
    dbBook.setAuthor(Book.getAuthor());
    return new ResponseEntity<Book>(dbBook, HttpStatus.OK);
  }

  @PatchMapping(value = "/books/{id}")
  public ResponseEntity<Book> updatePartially(@PathVariable("id") Long id,
    @RequestBody Book Book) {
    Book dbBook = bookRepository.findOne(id);
    if (dbBook == null) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
    dbBook.setName(Book.getName());
    dbBook.setAuthor(Book.getAuthor());
    return new ResponseEntity<Book>(dbBook, HttpStatus.OK);
  }
}
