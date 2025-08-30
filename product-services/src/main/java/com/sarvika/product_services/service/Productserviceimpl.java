package com.sarvika.product_services.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvika.product_services.dto.ProductRequestDTO;
import com.sarvika.product_services.dto.ProductResponseDTO;
import com.sarvika.product_services.entity.Products;
import com.sarvika.product_services.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Productserviceimpl implements Productservice {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public Productserviceimpl(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ProductResponseDTO saveProduct(ProductRequestDTO product) {
        Products products = objectMapper.convertValue(product,Products.class);
        return objectMapper.convertValue(productRepository.save(products),ProductResponseDTO.class);
    }

    @Override
    public String updateProduct(String id, ProductRequestDTO products) {
        return productRepository.findById(id).map(existingProduct -> {
            if (products.getName() != null) {
                existingProduct.setName(products.getName());
            }
            if (products.getPrice()==0.0) {
                existingProduct.setPrice(products.getPrice());
            }
            productRepository.save(existingProduct);
            return "Product updated successfully";
        }).orElse("Product not found with id: " + id);
    }

    @Override
    public ProductResponseDTO fetchProduct(String  id) {
        return productRepository.findById(id).map(p-> objectMapper.convertValue(p,ProductResponseDTO.class)).orElse(null);
    }

    @Override
    public List<ProductResponseDTO> fetchAllProduct() {
        List<Products> list = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS= new ArrayList<>();
        for (Products p : list){
            ProductResponseDTO productResponseDTO = objectMapper.convertValue(p,ProductResponseDTO.class);
            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }

    @Override
    public String deleteProduct(String id) {
        try {
            productRepository.deleteById(id);
            return "Product -"+ id+" delete successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
