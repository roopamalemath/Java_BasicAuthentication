package com.in28Minutes.rest.webservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		
		for(int i=1;i<=10;i++) {
			String encodeString=bCryptPasswordEncoder.encode("password@!23");
			System.out.println(encodeString);
		}

	}

}
