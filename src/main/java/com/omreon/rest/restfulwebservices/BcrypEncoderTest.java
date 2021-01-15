package com.omreon.rest.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.qos.logback.core.encoder.Encoder;

public class BcrypEncoderTest {

	public static void main(String[] args) {
	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedString;
		for(int i=0 ; i<10;i++) {
		encodedString = encoder.encode("akin12345.");
		System.out.println(encodedString);
		}
	}

}
