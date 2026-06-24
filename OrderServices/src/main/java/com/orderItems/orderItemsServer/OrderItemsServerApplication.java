package com.orderItems.orderItemsServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderItemsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderItemsServerApplication.class, args);
	}

}
