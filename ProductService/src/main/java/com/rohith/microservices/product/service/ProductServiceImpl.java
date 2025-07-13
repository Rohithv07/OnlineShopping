package com.rohith.microservices.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rohith.microservices.product.dto.ProductRequest;
import com.rohith.microservices.product.dto.ProductResponse;
import com.rohith.microservices.product.model.Product;
import com.rohith.microservices.product.repo.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = Product.builder().name(productRequest.name()).description(productRequest.description())
				.price(productRequest.price()).build();
		productRepository.save(product);
		log.info("Product created succesfully");
		return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		log.info("Collecting the products");
		return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(),
				product.getName(), product.getDescription(), product.getPrice())).toList();

	}
}
