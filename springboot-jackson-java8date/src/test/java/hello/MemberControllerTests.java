/*
 * Copyright 2016 the original author or authors.
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
package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMemberTest() throws Exception {

        this.mockMvc.perform(get("/member")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDate").value("2001-04-20"));
    }
    @Test
    public void testJava8DateTimeJacksonDefault() throws JsonProcessingException {
        Member member = new Member();
        member.setId(1L);
        member.setFirstName("Tom");
        member.setLastName("Sawyer");
        member.setBirthDate(LocalDate.of(2001, Month.APRIL, 20));
        member.setRegisteredDateTime(LocalDateTime.of(
                2017, Month.JUNE, 29, 20, 40, 59));

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(member));

    }

    @Test
    public void testJava8DateTimeJacksonJsr310() throws JsonProcessingException {
        Member member = new Member();
        member.setId(1L);
        member.setFirstName("Tom");
        member.setLastName("Sawyer");
        member.setBirthDate(LocalDate.of(2001, Month.APRIL, 20));
        member.setRegisteredDateTime(LocalDateTime.of(
                2017, Month.JUNE, 29, 20, 40, 59));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(member));

    }

}
