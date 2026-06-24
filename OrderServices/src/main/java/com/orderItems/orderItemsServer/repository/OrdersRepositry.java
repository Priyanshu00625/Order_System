package com.orderItems.orderItemsServer.repository;

import com.orderItems.orderItemsServer.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepositry extends JpaRepository<Order, Integer> {


    boolean deleteAllById(Long id);

}
