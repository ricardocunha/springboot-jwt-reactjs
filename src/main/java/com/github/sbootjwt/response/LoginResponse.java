package com.github.sbootjwt.response;

public class LoginResponse {
	public String token;
	public String name;
	public LoginResponse(final String token,final String name) {
		this.token = token;
	}

}
