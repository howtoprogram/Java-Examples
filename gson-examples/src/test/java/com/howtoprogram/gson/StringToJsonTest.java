package com.howtoprogram.gson;

import static org.junit.Assert.*;

import com.google.gson.*;
import org.junit.Test;

public class StringToJsonTest {

    @Test
    public void testStringToJsonObject() {

        String json = "{\"id\":1,\"name\":\"Thinking in Java\",\"author\":\"Bruce Eckel\"}";
        JsonObject jsonObj = new JsonParser().parse(json).getAsJsonObject();

        assertEquals(1, jsonObj.get("id").getAsInt());
        assertEquals("Thinking in Java", jsonObj.get("name").getAsString());
        assertEquals("Bruce Eckel", jsonObj.get("author").getAsString());

    }

    @Test
    public void testStringToJsonObjectWithJsonElement() {

        String json = "{\"id\":2,\"name\":\"Effective Java\",\"author\":\"Joshua Bloch\"}";

        Gson gson = new Gson();
        JsonElement element = gson.fromJson(json, JsonElement.class);
        JsonObject jsonObject = element.getAsJsonObject();

        assertEquals(2, jsonObject.get("id").getAsInt());
        assertEquals("Effective Java", jsonObject.get("name").getAsString());
        assertEquals("Joshua Bloch", jsonObject.get("author").getAsString());

    }
}
