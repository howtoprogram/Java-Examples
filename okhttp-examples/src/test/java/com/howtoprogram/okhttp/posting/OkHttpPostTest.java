package com.howtoprogram.okhttp.posting;

import static junit.framework.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class OkHttpPostTest {

 @Test
  public void testPostingWithString() throws IOException {

    OkHttpClient client = new OkHttpClient.Builder().build();
    MediaType textPlainMT = MediaType.parse("text/plain; charset=utf-8");
    String st = "Hello OkHttp";

    Request request = new Request.Builder().url("http://httpbin.org/post")
      .post(RequestBody.create(textPlainMT, st)).build();

    Response response = client.newCall(request).execute();

    assertTrue(response.isSuccessful());
    response.close();

  }

  private static final MediaType MEDIA_TYPE_PLAINTEXT = MediaType
    .parse("text/plain; charset=utf-8");
  private final OkHttpClient client = new OkHttpClient();

  @Test
  public void testPostMultipart()
    throws IOException {

    File file = new File("src/test/resources/Lorem Ipsum.txt");
    RequestBody requestBody = new MultipartBody.Builder()
      .setType(MultipartBody.FORM)

    .addFormDataPart("file", "LoremIpsum.txt",
      RequestBody.create(MEDIA_TYPE_PLAINTEXT, file))
      .addFormDataPart("description", "Just Lorem Ipsum")
      .build();

    Request request = new Request.Builder()
      .url("http://httpbin.org/post")
      .post(requestBody).build();

    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) {
      throw new IOException("Unexpected code " + response);
    }

  }

 @Test
  public void testPostForm() throws Exception {

    RequestBody formBody = new FormBody.Builder()
      .add("email", "user@howtoprogram.xyz")
      .add("firstname", "John").add("lastname", "Henry").build();

    Request request = new Request.Builder().url("http://httpbin.org/post")
      .post(formBody)
      .build();

    Response response = client.newCall(request).execute();

    assertTrue(response.isSuccessful());
  }

 @Test
  public void testPostByteArray() throws Exception {

    String st = "posting a byte array with OkHttp";

    Request request = new Request.Builder()
      .url("http://httpbin.org/post")
      .post(RequestBody.create(MEDIA_TYPE_PLAINTEXT, st.getBytes("UTF8")))
      .build();

    Response response = client.newCall(request).execute();

    assertTrue(response.isSuccessful());
  }

 @Test
  public void testPostStream() throws Exception {

    RequestBody requestBody = new RequestBody() {
      @Override
      public MediaType contentType() {
        return MEDIA_TYPE_PLAINTEXT;
      }

      @Override
      public void writeTo(BufferedSink sink) throws IOException {

        File file = new File("src/test/resources/Lorem Ipsum.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          String line;
          while ((line = br.readLine()) != null) {
            sink.writeUtf8(line);
          }
        }
      }

    };

    Request request = new Request.Builder()
      .url("http://httpbin.org/post")
      .post(requestBody)
      .build();

    Response response = client.newCall(request).execute();

    assertTrue(response.isSuccessful());
  }

  @Test
  public void testPostFile() throws Exception {
    File file = new File("src/test/resources/Lorem Ipsum.txt");

    Request request = new Request.Builder()
      .url("http://httpbin.org/post")
      .post(RequestBody.create(MEDIA_TYPE_PLAINTEXT, file))
      .build();

    Response response = client.newCall(request).execute();

    assertTrue(response.isSuccessful());
  }

}
