package com.orderItems.orderItemsServer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service" , url = "http://localhost:8084")
public interface FeignClients {
    @GetMapping("/product/id/{productId}")
    Product getProductById(@PathVariable("productId") Long productId);
}
