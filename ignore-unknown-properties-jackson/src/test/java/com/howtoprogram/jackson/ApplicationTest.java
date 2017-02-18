/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.howtoprogram.jackson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testIgnoreUnknownFields() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = "{\"id\": 1,\"name\": \"Core Java\",\"author\": \"Cay Hor.\",\"qty\": 2," +
                "\"rating\": 3.8}";

        try {
            Book javaBook = objectMapper.readValue(json, Book.class);

            assertNotNull(javaBook);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testIgnoreUnknownFieldsGlobally() {

        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = "{\"id\": 1,\"name\": \"Core Java\",\"author\": \"Cay Hor.\",\"qty\": 2," +
                "\"rating\": 3.8}";

        try {
            Book javaBook = objectMapper.readValue(json, Book.class);

            assertNotNull(javaBook);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
