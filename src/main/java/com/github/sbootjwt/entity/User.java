package com.github.sbootjwt.entity;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    private String id;

    private String name;
	private String email;
    private String password;

	public User() {}

    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}