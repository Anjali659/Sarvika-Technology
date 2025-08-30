package com.sarvika.product_services.repository;

import com.sarvika.product_services.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Products, String> {
}
