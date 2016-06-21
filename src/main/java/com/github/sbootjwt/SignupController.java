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
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	AuthenticationService authenticationService = null;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public LoginResponse signin(@RequestBody final UserDTO login) throws ServletException {
		return new LoginResponse(authenticationService.addUser(login),login.getName());
	}

}
