package com.genius.webfluxfuncional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WebfluxFuncionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxFuncionalApplication.class, args);
	}

}
