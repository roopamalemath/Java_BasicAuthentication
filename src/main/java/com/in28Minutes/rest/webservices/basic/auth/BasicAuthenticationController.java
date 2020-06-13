package com.in28Minutes.rest.webservices.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200")
@RestController // handling http request
public class BasicAuthenticationController {	
	

	@GetMapping(path="/basicauth")
	public AuthenticationBean helloWorldBean() {
		//throw new RuntimeException("some error has happened please contact Support at *****-********");
		return new AuthenticationBean("You are Authenticated");
	}
	
	
	
}
