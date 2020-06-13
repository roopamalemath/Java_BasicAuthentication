package com.in28Minutes.rest.webservices.RestfulWebServices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200")
@RestController // handling http request
public class HelloWorldController {	
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-Bean")
	public HelloWorldBean helloWorldBean() {
		//throw new RuntimeException("some error has happened please contact Support at *****-********");
		return new HelloWorldBean("Hello World changed");
	}
	
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathavariable(@PathVariable String name) {
		//throw new RuntimeException("some error has happened please contact Support at *****-********");
		return new HelloWorldBean(String.format("Hello, %s", name));
	}
}
