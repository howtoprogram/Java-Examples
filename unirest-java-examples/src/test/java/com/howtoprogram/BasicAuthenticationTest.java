package com.howtoprogram;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.utils.Base64Coder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class BasicAuthenticationTest {

  @Test
  public void testBasicAuthAPI() throws IOException, UnirestException {

    HttpResponse<JsonNode> response = Unirest.get("http://httpbin.org/basic-auth/user/passwd")
            .basicAuth("user", "passwd")
            .asJson();
    assertThat(response.getStatus(), equalTo(200));
  }

  @Test
  public void testBasicAuthRawHeader() throws IOException, UnirestException {

    HttpResponse<JsonNode> response = Unirest.get("http://httpbin.org/basic-auth/user/passwd").
            header("Authorization", "Basic " + Base64Coder.encodeString("user" + ":" + "passwd"))
            .asJson();

    assertThat(response.getStatus(), equalTo(200));
  }
}
