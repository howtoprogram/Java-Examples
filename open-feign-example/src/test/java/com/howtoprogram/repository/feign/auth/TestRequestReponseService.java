package com.howtoprogram.repository.feign.auth;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestRequestReponseService {

	@Test
	public void testAuthenticate() {
		RequestReponseService service = new RequestReponseService();
		AuthStatus authStatus = service.getAuthenticatedUser();
		assertEquals(authStatus.getUser(), RequestReponseService.USERNAME);
		assertTrue(authStatus.isAuthenticated());

	}

}
