package com.howtoprogram.repository.feign.auth;

import feign.Headers;
import feign.RequestLine;

@Headers("Accept: application/json")
interface HttpBinResource {

	@RequestLine("GET /basic-auth/user/passwd")
	AuthStatus getAuthenticatedUser();
}