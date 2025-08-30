package com.sarvika.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Product" , url= "${service.product}")
public interface ProductFeign {
    @GetMapping("/products/{id}")
    ResponseEntity<?> fetchProductById(@PathVariable String id);

    @GetMapping("/products")
    ResponseEntity<?> fetchAllProduct();
}
