package com.genius.webfluxfuncional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableReactiveFeignClients
public class WebfluxFuncionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxFuncionalApplication.class, args);
	}

}
