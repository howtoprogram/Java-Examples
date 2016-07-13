package com.howtoprogram.repository.resteasyproxy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.howtoprogram.domain.Book;

public interface SimpleResteasyProxyClient {

  @GET
  @Consumes("application/json")
  List<Book> getAllBooks();

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  Book createBook(Book book);

  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  Book updateBook(@PathParam("id") Long id, Book book);

  @DELETE
  @Path("/{id}")
  @Consumes
  void deleteBook(@PathParam("id") Long id);

}
