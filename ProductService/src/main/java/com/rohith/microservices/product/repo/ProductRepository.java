package com.rohith.microservices.product.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rohith.microservices.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
