package com.omreon.rest.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4040")
public class BasicAuthenticationService {
	
	@GetMapping(path = "/basicAuth")
	public String basicAuthentication() {

		return  "You Are Authenticated User";

	}

}
