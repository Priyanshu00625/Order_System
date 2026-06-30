package com.orderItems.orderItemsServer.client;

import com.orderItems.orderItemsServer.config.FeignConfig;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "USER-SERVICE" , url = "http://USER-SERVICE/user" , configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/userName")
    String getUserName();
}
