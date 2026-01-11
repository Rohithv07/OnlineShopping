package com.rohith.microservices.order.service;

import com.rohith.microservices.order.dto.OrderRequest;

public interface OrderService {
	public void placeOrder(OrderRequest orderRequest);
	
}
