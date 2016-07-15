package com.howtoprogram.repository.cxf;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.howtoprogram.domain.Book;

public interface BookResource {

  @GET
  List<Book> getAllBooks();

  @POST
  Book createBook(Book book);

  @PUT
  @Path("/{id}")
  Book updateBook(@PathParam("id") Long id, Book book);

  @DELETE
  @Path("/{id}")
  void deleteBook(@PathParam("id") Long id);

}
