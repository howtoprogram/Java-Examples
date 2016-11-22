package com.howtoprogram.repository.feign.auth;

public class AuthStatus {
	private boolean authenticated;
	private String user;

	// getters and setters are omitted for shortly

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
