package com.howtoprogram;

import com.howtoprogram.filters.pre.RequestLoggerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class SimpleEdgeApplication {

  public static void main(String[] args) {
    SpringApplication.run(SimpleEdgeApplication.class, args);
  }

  @Bean
  public RequestLoggerFilter simpleFilter() {
    return new RequestLoggerFilter();
  }

}
