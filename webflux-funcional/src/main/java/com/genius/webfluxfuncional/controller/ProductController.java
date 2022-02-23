package com.genius.webfluxfuncional.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genius.webfluxfuncional.client.ProductClient;
import com.genius.webfluxfuncional.client.response.ProductResponse;

import feign.Response;
import lombok.AllArgsConstructor;
import lombok.experimental.var;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
	private final ProductClient productClient;
	
	@GetMapping
	public Flux<ProductResponse> getListProducts(){
		var response = productClient.getProducts();
		return response;
	}
}