package com.genius.tokenservice;

import com.genius.tokenservice.property.JwtProperty;
import com.genius.tokenservice.property.PBKDF2Property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
//@EnableWebFlux
@EnableConfigurationProperties(JwtProperty.class)
public class TokenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenServiceApplication.class, args);
	}

}
