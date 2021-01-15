package com.omreon.rest.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4040")
public class HelloWorldController {

	@GetMapping(path = "/helloWorld")
	public String helloWorld() {
		return "helloWorldFromString";
	}

	@GetMapping(path = "/helloWorldBean")
	public HelloWorldBean helloWorldBean() {

		return new HelloWorldBean("");

	}
	
	@GetMapping(path = "/helloWorld/{name}")
	public HelloWorldBean helloWorldBeanWithPathVariable(@PathVariable String name) {
		//throw new RuntimeException("Something Went Wrong!");
		return new HelloWorldBean(String.format("(This Message From %s)",name));

	}

}
