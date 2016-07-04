package com.howtoprogram.restful;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.howtoprogram.domain.Book;

public class BookRepositoryImplJackson {


  /**
   * Gets all {@link Book} from RESTful Web Service; then, use Jackson library to convert the JSON
   * response to Java objects
   */
  public void getAllBooksAsJson() {
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    String json = null;
    try {
      URL resetEndpoint = new URL("http://localhost:8080/v1/books");
      connection = (HttpURLConnection) resetEndpoint.openConnection();
      // Set request method to GET as required from the API
      connection.setRequestMethod("GET");

      // Read the response
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder jsonSb = new StringBuilder();
      String line = null;
      while ((line = reader.readLine()) != null) {
        jsonSb.append(line);
      }
      json = jsonSb.toString();

      // Converts JSON string to Java object
      ObjectMapper mapper = new ObjectMapper();
      // Converts to an array of Book
      Book[] books = mapper.readValue(json, Book[].class);
      for (Book book : books) {
        System.out.println(book);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates {@link Book} by posting the XML to RESTful Web Service. The XML content is converted
   * from Java object using Jackson API
   */

  public void createBookAsXML() {
    try {
      URL url = new URL("http://localhost:8080/v1/books");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      // Set the request method to POST as required from the API
      con.setRequestMethod("POST");

      // Set the Content-Type to "application/xml" as required from the API
      con.setRequestProperty("Content-Type", "application/xml");
      con.setDoOutput(true);

      OutputStream os = con.getOutputStream();
      // The book we want to create in JSON format
      // String book = "<book><name>Effective Java</name><author>Joshua Bloch</author></book>";
      // Creates new Book instance
      Book book = new Book(null, "Effective Java", "Joshua Bloch");
      JacksonXmlModule module = new JacksonXmlModule();
      // and then configure, for example:
      module.setDefaultUseWrapper(false);
      XmlMapper xmlMapper = new XmlMapper(module);
      System.out.println(xmlMapper.writeValueAsString(book));

      os.write(xmlMapper.writeValueAsBytes(book));
      os.flush();
      os.close();

      int responseCode = con.getResponseCode();

      System.out.println("Response Code :" + responseCode);

      if (responseCode == HttpURLConnection.HTTP_CREATED) {
        System.out.println("Created book successfully.");
      } else {
        System.out.println("Created book failed.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  /**
   * Creates {@link Book} by posting the JSON to RESTful Web Service. The JSON content is converted
   * from Java object using Jackson API
   */

  public void createBookAsJSON() {
    try {
      URL url = new URL("http://localhost:8080/v1/books");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      // Set the request method to POST as required from the API
      con.setRequestMethod("POST");

      // Set the Content-Type to "application/json" as required from the API
      con.setRequestProperty("Content-Type", "application/json");
      con.setDoOutput(true);

      OutputStream os = con.getOutputStream();
      // The book we want to create in JSON format
      // String book = "{\"name\":\"Effective Java\",\"author\":\"Joshua Bloch\"}";
      // Creates new Book instance
      Book book = new Book(null, "Effective Java", "Joshua Bloch");
      ObjectMapper mapper = new ObjectMapper();
      os.write(mapper.writeValueAsBytes(book));
      os.flush();
      os.close();

      int responseCode = con.getResponseCode();

      System.out.println("Response Code :" + responseCode);

      if (responseCode == HttpURLConnection.HTTP_CREATED) {
        System.out.println("Created book successfully.");
      } else {
        System.out.println("Created book failed.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


}
