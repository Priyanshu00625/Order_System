package com.orderItems.orderItemsServer.ordersController;

import com.orderItems.orderItemsServer.entity.Order;
import com.orderItems.orderItemsServer.ordersServices.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersControllers {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> Save_new_order(@RequestBody Order orders){
       try{
           orderService.saveOrders(orders);
           return new ResponseEntity<>(orders , HttpStatus.CREATED);
       } catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }
    @GetMapping
    public ResponseEntity<List<Order>> getOrders(){
        try{
            List<Order> allOrders = orderService.findAllOrders();
            return new ResponseEntity<>(allOrders, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @DeleteMapping
    public ResponseEntity<?> removeOrders(@RequestBody Order orders){
      try{
          orderService.deleteOrder(orders);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
}
