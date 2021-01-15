package com.omreon.rest.restfulwebservices.helloworld;

public class HelloWorldBean {

	String helloWorldString = " Hello World From Omreon!";

	public HelloWorldBean(String helloWorldPathVar) 
	{
		this.helloWorldString = helloWorldString + helloWorldPathVar ; 
	}

	public String getHelloWorldString() {
		return helloWorldString;
	}

	public void setHelloWorldString(String helloWorldPathVar) {
		this.helloWorldString = helloWorldString + helloWorldPathVar;
	}
	
	

}
