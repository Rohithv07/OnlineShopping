package com.rohith.microservices.product.service;

import java.util.List;

import com.rohith.microservices.product.dto.ProductRequest;
import com.rohith.microservices.product.dto.ProductResponse;

public interface ProductService {
	public ProductResponse createProduct(ProductRequest productRequest);

	public List<ProductResponse> getAllProducts();
}
