package com.howtoprogram.gson;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.howtoprogram.jsonpojo.Book;

public class JacksonTest {

	/**
	 * Converts a JSON string to a {@link Book} object
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testConvertJsonToObject() throws Exception {

		String json = "{\"id\":2,\"name\":\"Thinking in Java\",\"author\":\"Bruce Eckel\"}";
		// create Jackson ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		// Converts JSON string to Java object
		Book book = mapper.readValue(json, Book.class);

		assertEquals(2, book.getId().longValue());
		assertEquals("Thinking in Java", book.getName());
		assertEquals("Bruce Eckel", book.getAuthor());
	}

	/**
	 * Converts a JSON string to a list of {@link Book}
	 */
	public static void toList() {

		String json = "[{\"id\":1,\"name\":\"Java How To Program\",\"author\":\"Paul Deitel\"}, "
		        + "{\"id\":2,\"name\":\"Thinking in Java\",\"author\":\"Bruce Eckel\"}]";

		try {
			ObjectMapper mapper = new ObjectMapper();
			List<Book> books = mapper.readValue(json, new TypeReference<List<Book>>() {
			});
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

		String json = "[{\"id\":1,\"name\":\"Java How To Program\",\"author\":\"Paul Deitel\"}, "
		        + "{\"id\":2,\"name\":\"Thinking in Java\",\"author\":\"Bruce Eckel\"}]";
		try {
			ObjectMapper mapper = new ObjectMapper();
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

		List<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(books);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts a list of {@link Book} to JSON
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void convertMapToJsonTest() throws JsonProcessingException {
		Book ilBook = new Book(1l, "The Immortalists", "Chloe Benjamin");
		Book gdrmBook = new Book(2l, "The Girl Who Drank the Moon", "Kelly Barnhill");
		Map<Long, Book> bookMap = new HashMap<Long, Book>();
		bookMap.put(ilBook.getId(), ilBook);
		bookMap.put(gdrmBook.getId(), gdrmBook);

		// Converts the list to JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookMap);

		assertTrue(json.contains(ilBook.getName()));
		assertTrue(json.contains(gdrmBook.getName()));

		System.out.println(json);
	}

	/**
	 * Converts a list of {@link Book} to JSON
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void convertCollectionToJsonTest() throws JsonProcessingException {
		Book h2prBook = new Book(1l, "Java How To Program", "Paul Deitel");
		Book thinkJavaBook = new Book(2l, "Thinking in Java", "Bruce Eckel");
		List<Book> books = new ArrayList<Book>();
		books.add(h2prBook);
		books.add(thinkJavaBook);

		// Converts the list to JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(books);

		assertTrue(json.contains(h2prBook.getName()));
		assertTrue(json.contains(thinkJavaBook.getName()));

		System.out.println(json);

	}

	/**
	 * Converts a list of {@link Book} to JSON
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void collectionToJSONPrettyPrintTest() throws JsonProcessingException {
		Book book1 = new Book(1l, "Java How To Program", "Paul Deitel");
		Book book2 = new Book(2l, "Thinking in Java", "Bruce Eckel");
		List<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);

		// Converts the list to JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(books);
		System.out.println(json);

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

	@Test
	public void testConvertObjectToJSON() throws JsonProcessingException {
		Book shBook = new Book(1l, "Sour Heart", "Jenny Zhang");

		// Converts the shBook to JSON
		ObjectMapper mapper = new ObjectMapper();
		// String json =
		// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(shBook);
		String json = mapper.writeValueAsString(shBook);
		assertTrue(json.contains("Sour Heart"));
		assertTrue(json.contains("Jenny Zhang"));
		System.out.println(json);

	}

	/**
	 * Read JSON content from the file: d:/books.json and convert to an array.
	 * Assume that the d:/books.json was exist.
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
