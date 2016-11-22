package com.howtoprogram.repository.feign.auth;

import feign.Feign;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class RequestReponseService {
	private static final String HTTP_BIN_URL = "http://httpbin.org/";
	public static final String USERNAME = "user";
	public static final String PASSWORD = "passwd";

	public AuthStatus getAuthenticatedUser() {

		BasicAuthRequestInterceptor interceptor = new BasicAuthRequestInterceptor(USERNAME,
		        PASSWORD);
		HttpBinResource bookResource = Feign.builder().encoder(new JacksonEncoder())
		        .decoder(new JacksonDecoder()).requestInterceptor(interceptor)
		        .target(HttpBinResource.class, HTTP_BIN_URL);
		return bookResource.getAuthenticatedUser();

	}

}
