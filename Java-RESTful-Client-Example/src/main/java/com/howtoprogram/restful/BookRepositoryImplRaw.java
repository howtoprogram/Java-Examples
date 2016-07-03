package com.howtoprogram.restful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.howtoprogram.domain.Book;

public class BookRepositoryImplRaw {

  /**
   * Get all {@link Book} from RESTful Web Service
   */
  public String getAllBooksAsJson() {
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    String retVal = null;
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
      retVal = jsonSb.toString();

      // print out the json response
      System.out.println(retVal);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // Clean up
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        connection.disconnect();
      }
    }
    return retVal;
  }


  /**
   * Creates {@link Book} by posting the XML to RESTful Web Service
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
      String book = "<book><name>Effective Java</name><author>Joshua Bloch</author></book>";
      os.write(book.getBytes());
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
   * Creates {@link Book} by posting the JSON to RESTful Web Service
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
      String book = "{\"name\":\"Effective Java\",\"author\":\"Joshua Bloch\"}";
      os.write(book.getBytes());
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
