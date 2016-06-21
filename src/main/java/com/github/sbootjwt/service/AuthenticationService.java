package com.github.sbootjwt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sbootjwt.dto.UserDTO;
import com.github.sbootjwt.entity.User;
import com.github.sbootjwt.repository.UserRepository;
import com.github.sbootjwt.response.LoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationService {
	private final Map<String, String> user = new HashMap<>();
	@Autowired
	private UserRepository repository;
	
	public AuthenticationService() {

	}
	
	public String getAuthToken(String email, String password){
		return Jwts.builder().setSubject(email).claim("roles", user.get(email))
		.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, password).compact();
	}
	
    public LoginResponse auth(final UserDTO login)
            throws ServletException {
        	User user = null;
            if (login.getEmail() == null || login.getPassword() == null) {
                throw new ServletException("Invalid username or password");
            } else {
            	user = repository.findByEmailAndPassword(login.getEmail(), login.getPassword());
            }
            if (user == null){
            	throw new ServletException("Invalid username or password");
            }
            return new LoginResponse(getAuthToken(login.getEmail(), login.getPassword()),login.getName());
        }
	
	public String addUser(final UserDTO login){
		User u = new User();
		u.setEmail(login.getEmail());
		u.setName(login.getName());
		u.setPassword(login.getPassword());
		repository.insert(u);
		return getAuthToken( login.getEmail(), login.getPassword());
	}
}
