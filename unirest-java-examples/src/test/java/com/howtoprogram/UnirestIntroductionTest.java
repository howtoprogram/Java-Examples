package com.howtoprogram;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by nangs on 12/31/2016.
 */
public class UnirestIntroductionTest {
    private static final String BASE_URL = "http://httpbin.org/get";


    @Test
    public void getWithParameters() throws IOException, UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL).queryString("username", "admin").asJson();
      assertTrue(response.getStatus()==200);

    }

    @Test
    public void getWithParametersVal() throws IOException, UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL).queryString("username", "admin").asJson();
        String value =  response.getBody().getObject().getJSONObject("args").get("username").toString();
       assertEquals(value,"admin");

    }

    @Test
    public void getASynchronously() throws IOException {
        Future<HttpResponse<JsonNode>> future = Unirest.get(BASE_URL).queryString("username", "admin")
                .header("accept", "application/json")
                .asJsonAsync(new Callback<JsonNode>() {

                    public void failed(UnirestException e) {
                        fail("The request has failed");
                    }

                    public void completed(HttpResponse<JsonNode> response) {
                        assertTrue(response.getStatus()==200);
                    }

                    public void cancelled() {
                        fail("The test is cancelled");
                    }

                });
    }
    @Test
    public void postWithUnirest() throws IOException, UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL).queryString("username", "admin").asJson();
        String value =  response.getBody().getObject().getJSONObject("args").get("username").toString();
        assertEquals(value,"admin");

    }

}
