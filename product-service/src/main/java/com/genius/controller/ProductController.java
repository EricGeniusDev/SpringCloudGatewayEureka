package com.genius.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genius.response.ProductResponse;

import feign.Response;
import lombok.experimental.var;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping
	public Flux<ProductResponse> returnsProductsList(){
		var response = Arrays.asList(new ProductResponse("123123"),new ProductResponse("asdasdkhj@31"));
		return Flux.fromIterable(response);
	}

}
