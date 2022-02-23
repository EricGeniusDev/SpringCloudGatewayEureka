package com.genius.webfluxfuncional.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.genius.webfluxfuncional.client.response.ProductResponse;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name="product-service")
public interface ProductClient {
	
	@GetMapping("/product")
	Flux<ProductResponse> getProducts();

}
