package com.sarvika.product_services.service;

import com.sarvika.product_services.dto.ProductRequestDTO;
import com.sarvika.product_services.dto.ProductResponseDTO;

import java.util.List;

public interface Productservice {
    ProductResponseDTO saveProduct(ProductRequestDTO product);

    String updateProduct(String id, ProductRequestDTO products);

    ProductResponseDTO fetchProduct(String id);

    List<ProductResponseDTO> fetchAllProduct();

    String deleteProduct(String id);
}
