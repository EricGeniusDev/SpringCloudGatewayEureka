package com.genius.webfluxfuncional.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genius.webfluxfuncional.properties.EmailProperties;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/property")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PropertyController {

	@Autowired
	EmailProperties properties;

	@GetMapping("/email")
	public Mono<EmailProperties> returnProperties() {
		return Mono.just(properties);
	}

	@RequestMapping

	public Mono<String> sa() {
		return Mono.just("properties");
	}
}
