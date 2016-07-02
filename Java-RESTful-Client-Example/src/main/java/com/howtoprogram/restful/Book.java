package com.howtoprogram.restful;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "book")
public class Book {

  private Long id;
  private String name;
  private String author;


  public Book() {
    super();
  }

  public Book(Long id, String name, String author) {
    super();
    this.id = id;
    this.name = name;
    this.author = author;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", name=" + name + ", author=" + author + "]";
  }


}
