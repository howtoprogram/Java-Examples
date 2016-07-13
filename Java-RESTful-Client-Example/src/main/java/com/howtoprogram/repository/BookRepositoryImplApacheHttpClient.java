package com.howtoprogram.repository;

import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.util.HttpAsyncClientUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howtoprogram.domain.Book;

public class BookRepositoryImplApacheHttpClient {
  private static final String URI_BOOK = "http://localhost:8080/v1/books";

  public void deleteBook(Long id) throws Exception {
    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    try {
      httpclient.start();
      // Create a delete method instance.
      HttpDelete request = new HttpDelete(URI_BOOK + "/" + id);
      Future<HttpResponse> future = httpclient.execute(request, null);
      // Wait and retrieve the result
      HttpResponse response = future.get();
      System.out.println("Response code:" + response.getStatusLine().getStatusCode());
      // Determine whether the request was successfully or not
      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) {
        throw new RuntimeException("Failed to delete the book with id:" + id);
      }
    } finally {
      HttpAsyncClientUtils.closeQuietly(httpclient);
    }
  }
  

  public Book updateBook(Book book) throws Exception {

    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    Book createdBook = null;
    try {
      httpclient.start();
      // Create a HttpPut instance.
      HttpPut request = new HttpPut(URI_BOOK + "/" + book.getId());
      request.setHeader("Content-type", "application/json");
      // Create new instance of ObjectMapper
      ObjectMapper mapper = new ObjectMapper();
      String jsonBook = mapper.writeValueAsString(book);
      StringEntity entity = new StringEntity(jsonBook);
      // Set the entity for the request
      request.setEntity(entity);

      Future<HttpResponse> future = httpclient.execute(request, null);
      // Wait and retrieve the result
      HttpResponse response = future.get();
      System.out.println("Response code:" + response.getStatusLine().getStatusCode());
      // Determine whether the request was successfully or not
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        // Get back the updated book
        createdBook = mapper.readValue(response.getEntity().getContent(), Book.class);
      }

    } finally {
      HttpAsyncClientUtils.closeQuietly(httpclient);
    }
    return createdBook;

  }

  public Book createBook(Book book) throws Exception {

    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    Book createdBook = null;
    try {
      httpclient.start();
      // Create a delete method instance.
      HttpPost request = new HttpPost(URI_BOOK);
      request.setHeader("Content-type", "application/json");
      // Create new instance of ObjectMapper
      ObjectMapper mapper = new ObjectMapper();
      String jsonBook = mapper.writeValueAsString(book);
      StringEntity entity = new StringEntity(jsonBook);
      // Set the entity for the request
      request.setEntity(entity);

      Future<HttpResponse> future = httpclient.execute(request, null);
      // Wait and retrieve the result
      HttpResponse response = future.get();
      System.out.println("Response code:" + response.getStatusLine().getStatusCode());
      // Determine whether the request was successfully or not
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
        // Get back the created book
        createdBook = mapper.readValue(response.getEntity().getContent(), Book.class);

      }

    } finally {
      HttpAsyncClientUtils.closeQuietly(httpclient);
    }
    return createdBook;

  }

  public Book[] getAllBooks() throws Exception {
    Book[] books = null;
    // Create an asyn HttpClient
    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    try {
      httpclient.start();
      HttpGet request = new HttpGet(URI_BOOK);
      Future<HttpResponse> future = httpclient.execute(request, null);
      // Wait and retrieve the result
      HttpResponse response = future.get();
      System.out.println("Response code:" + response.getStatusLine().getStatusCode());
      // Determine whether the request was successfully or not
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        HttpEntity httpEntity = response.getEntity();
        // Create a Jackson ObjectMapper to convert the JSON response to Java objects
        ObjectMapper mapper = new ObjectMapper();
        // Read the inputstream and convert to an array of Book
        books = mapper.readValue(httpEntity.getContent(), Book[].class);
      }
    } finally {
      HttpAsyncClientUtils.closeQuietly(httpclient);
    }
    return books;
  }



  public static void main(String[] args) throws Exception {
    BookRepositoryImplApacheHttpClient bookRepository = new BookRepositoryImplApacheHttpClient();
    // Getting the first book from the RESTful service
    Book book = bookRepository.getAllBooks()[0];
    bookRepository.deleteBook(book.getId());

  }



  public Book findBookById(Long id) {
    return null;
  }

}
