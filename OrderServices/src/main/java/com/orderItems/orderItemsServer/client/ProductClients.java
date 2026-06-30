package com.orderItems.orderItemsServer.client;

import com.orderItems.orderItemsServer.config.FeignConfig;
import com.orderItems.orderItemsServer.dto.Product;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE" , url = "http://PRODUCT-SERVICE/product" , configuration = FeignConfig.class)
public interface ProductClients {
    @GetMapping("/all")
    List<Product> findAll();
    @GetMapping("/id/{productId}")
    Product getProductById(@PathVariable("productId") String productId);
}
