package com.howtoprogram.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class BookController {
  private static List<Book> books = new ArrayList<>();

  public BookController() {
    books.add(new Book(1l, "Java How To Program", "Paul Deitel"));
    books.add(new Book(2l, "Thinking in Java", "Bruce Eckel"));
  }

  @RequestMapping(value = "/books", method = RequestMethod.GET)
  public ResponseEntity<Iterable<Book>> getAllBooks() {
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @RequestMapping(value = "/books", method = RequestMethod.POST)
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    books.add(book);
    book.setId(Long.valueOf(books.size() + 1));
    System.out.println(book);
    return new ResponseEntity<>(book, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/books/{id}")
  public ResponseEntity<Book> readBook(@PathVariable("id") Long id) {
    Optional<Book> fBook = books.stream().filter(b -> b.getId().equals(id))
      .distinct()
      .findFirst();
    if (fBook.isPresent()) {
      return new ResponseEntity<>(fBook.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
    boolean found = false;
    for (Iterator<Book> i = books.iterator(); i.hasNext();) {
      Book item = i.next();
      if (item.getId().equals(id)) {
        i.remove();
        found = true;
        break;
      }
    }
    if (!found) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,
    @RequestBody Book Book) {
    Book currentBook = null;
    for (Book book : books) {
      if (book.getId().equals(id)) {
        currentBook = book;
        break;
      }
    }
    if (currentBook == null) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    currentBook.setName(Book.getName());
    currentBook.setAuthor(Book.getAuthor());
    return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
  }
}
