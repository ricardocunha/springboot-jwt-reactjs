package com.github.sbootjwt;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.sbootjwt.dto.UserDTO;
import com.github.sbootjwt.response.LoginResponse;
import com.github.sbootjwt.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/signin")
public class SignInController {

	@Autowired
	AuthenticationService authenticationService = null;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "auth", method = RequestMethod.POST)
	public LoginResponse auth(@RequestBody final UserDTO login) throws ServletException {

 		return authenticationService.auth(login);
	}

}