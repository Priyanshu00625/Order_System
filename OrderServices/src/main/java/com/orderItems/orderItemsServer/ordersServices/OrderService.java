package com.orderItems.orderItemsServer.ordersServices;

import com.orderItems.orderItemsServer.entity.Order;
import com.orderItems.orderItemsServer.repository.OrdersRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepositry ordersRepositry;

    public boolean saveOrders(Order orders){
        Order save = ordersRepositry.save(orders);

        if (save.equals(true)){
            return true;
        }else {
            return false;
        }

    }

    public List<Order> findAllOrders(){
       return ordersRepositry.findAll();
    }

    public boolean deleteOrder(Order orders){
        Long id = orders.getId();
      return ordersRepositry.deleteAllById(id);
    }

}
