package com.orderItems.orderItemsServer.repository;

import com.orderItems.orderItemsServer.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepositry extends JpaRepository<Order, Integer> {


    boolean deleteAllById(Integer id);

    Optional<Order> findByUserNameAndProductId(String userId, String productId);
    Optional<Order> findById(Long id);

}
