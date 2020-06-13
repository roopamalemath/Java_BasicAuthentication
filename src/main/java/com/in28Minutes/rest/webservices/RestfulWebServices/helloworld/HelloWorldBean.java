package com.in28Minutes.rest.webservices.RestfulWebServices.helloworld;

public class HelloWorldBean  {

	private String message;

	public String getMessage() {
		return message;
	}

	public HelloWorldBean(String message) {
		this.message=message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
