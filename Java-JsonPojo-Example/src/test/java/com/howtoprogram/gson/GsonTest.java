package com.howtoprogram.gson;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.howtoprogram.jsonpojo.Book;

public class GsonTest {

	@Test
	public void testConvertOjectToJsonTest() {
		Book milkHoneyBook = new Book(1000L, "Milk and Honey", "Rupi Kaur");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(milkHoneyBook);

		assertTrue(json.contains("Milk and Honey"));
		System.out.print(json);
	}

	@Test
	public void testConvertListToJsonTest() {
		Book sfBook = new Book(1001L, "StrengthsFinder 2.0", "Tom Rath");
		Book pHBook = new Book(1002L, "The Power of Habit", "Charles Duhigg");
		List<Book> books = new ArrayList<Book>();
		books.add(sfBook);
		books.add(pHBook);

		// Converts the list to JSON
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(books);
		assertTrue(json.contains("StrengthsFinder 2.0"));
		assertTrue(json.contains("The Power of Habit"));

		System.out.println(json);
	}

	@Test
	public void testConvertSetToJsonTest() {
		Book litteFireBook = new Book(1001L, "Little Fires Everywhere", "Celeste Ng");
		Book leoDaVinciBook = new Book(1002L, "Leonardo da Vinci", "Walter Isaacson");
		Set<Book> books = new HashSet<Book>();
		books.add(litteFireBook);
		books.add(leoDaVinciBook);

		// Converts the set to JSON
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(books);
		assertTrue(json.contains("Little Fires Everywhere"));
		assertTrue(json.contains("Leonardo da Vinci"));

		System.out.println(json);
	}

	@Test
	public void testConvertMapToJsonTest() {
		Book eoBook = new Book(1l, "Extreme Ownership", "Jocko Willink");
		Book lopBook = new Book(2l, "The 48 Laws of Power", "Robert Greene");
		Map<Long, Book> booksMap = new HashMap<Long, Book>();
		booksMap.put(eoBook.getId(), eoBook);
		booksMap.put(lopBook.getId(), lopBook);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(booksMap);

		assertTrue(json.contains("Extreme Ownership"));
		assertTrue(json.contains("The 48 Laws of Power"));
		System.out.println(json);
	}

}
