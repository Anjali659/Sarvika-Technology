package com.sarvika.product_services.controller;

import com.sarvika.product_services.dto.ProductRequestDTO;
import com.sarvika.product_services.service.Productservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private  final Productservice productService;

    public ProductController(Productservice productService){
        this.productService=productService;
    }
    @PostMapping()
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO product){
        return  ResponseEntity.ok(productService.saveProduct(product));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateProduct( @PathVariable String id,@RequestBody ProductRequestDTO users){
        return  ResponseEntity.ok(productService.updateProduct(id,users));
    }
    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable String id){
        return  ResponseEntity.ok(productService.fetchProduct(id));
    }
    @GetMapping()
    public  ResponseEntity getAllProduct(){
        return  ResponseEntity.ok(productService.fetchAllProduct());
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity deleteProduct(@PathVariable String id){
        return  ResponseEntity.ok(productService.deleteProduct(id));
    }

}
