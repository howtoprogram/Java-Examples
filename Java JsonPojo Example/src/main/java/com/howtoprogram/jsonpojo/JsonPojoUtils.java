package com.howtoprogram.jsonpojo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPojoUtils {

  public static void main(String[] args) {
    // JSON to object
    toObject();
    toList();
    toArray();

    // Object to JSON
    toJSON();
    collectionToJSON();
    // Working with file
    serializeToFile();
    readFromFile();

  }

  /**
   * Converts a JSON string to a {@link Book} object
   */
  public static void toObject() {

    String json = "{\"id\":2,\"name\":\"Thinking in Java\",\"author\":\"Bruce Eckel\"}";
    // create Jackson ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    try {
      // Converts JSON string to Java object
      Book book = mapper.readValue(json, Book.class);

      System.out.println(book);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Converts a JSON string to a list of {@link Book}
   */
  public static void toList() {

    String json =
        "[{\"id\":1,\"name\":\"Java How To Program\",\"author\":\"Paul Deitel\"}, {\"id\":2,\"name\":\"Thinking in Java\",\"author\":\"Bruce Eckel\"}]";
    // create Jackson ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    try {
      // Converts JSON string to Java object
      List<Book> books = mapper.readValue(json, new TypeReference<List<Book>>() {});
      System.out.println(books);
      // Or converted from the array
      books = Arrays.asList(mapper.readValue(json, Book[].class));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Converts a JSON string to an array of {@link Book}
   */
  public static void toArray() {

    String json =
        "[{\"id\":1,\"name\":\"Java How To Program\",\"author\":\"Paul Deitel\"}, {\"id\":2,\"name\":\"Thinking in Java\",\"author\":\"Bruce Eckel\"}]";
    // create Jackson ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    try {
      // Converts JSON string to an array
      Book[] booksArray = mapper.readValue(json, Book[].class);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Converts a lit of {@link Book} to JSON string.
   */
  public static void toJSON() {
    Book book1 = new Book(1l, "Java How To Program", "Paul Deitel");
    Book book2 = new Book(2l, "Thinking in Java", "Bruce Eckel");

    // Converts an Java object to JSON string
    ObjectMapper mapper = new ObjectMapper();
    try {
      String json = mapper.writeValueAsString(book1);
      System.out.println(json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    List<Book> books = new ArrayList<Book>();
    books.add(book1);
    books.add(book2);
    try {
      String json = mapper.writeValueAsString(books);
      System.out.println(json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Converts a list of {@link Book} to JSON
   */
  public static void collectionToJSON() {
    Book book1 = new Book(1l, "Java How To Program", "Paul Deitel");
    Book book2 = new Book(2l, "Thinking in Java", "Bruce Eckel");
    List<Book> books = new ArrayList<Book>();
    books.add(book1);
    books.add(book2);

    // Converts an Java object to JSON string
    ObjectMapper mapper = new ObjectMapper();

    try {
      // Converts the list to JSON
      String json = mapper.writeValueAsString(books);
      System.out.println(json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Serializes a list of {@link Book} to the file: d:/books.json
   */
  public static void serializeToFile() {
    Book book1 = new Book(1l, "Java How To Program", "Paul Deitel");
    Book book2 = new Book(2l, "Thinking in Java", "Bruce Eckel");

    // Converts an Java object to JSON string
    ObjectMapper mapper = new ObjectMapper();
    try {
      String json = mapper.writeValueAsString(book1);
      System.out.println(json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    List<Book> books = new ArrayList<Book>();
    books.add(book1);
    books.add(book2);
    try {
      // Serialize to file
      mapper.writeValue(new File("d:/books.json"), books);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Read JSON content from the file: d:/books.json and convert to an array. Assume that the
   * d:/books.json was exist.
   */
  public static void readFromFile() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      Book[] books = mapper.readValue(new File("d:/books.json"), Book[].class);
      System.out.println(books);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}


