package com.howtoprogram.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignBookConfiguration {

  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    return new BasicAuthRequestInterceptor("user", "password");
  }

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  public static final int TEN_SECONDS = 10000;

  @Bean
  public Request.Options options() {
    return new Request.Options(TEN_SECONDS, TEN_SECONDS);
  }

}
