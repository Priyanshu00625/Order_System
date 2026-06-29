package com.orderItems.orderItemsServer.ordersController;

import com.orderItems.orderItemsServer.dto.Product;
import com.orderItems.orderItemsServer.entity.Order;
import com.orderItems.orderItemsServer.ordersServices.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrdersControllers {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{productId}")
    public ResponseEntity<Order> Save_new_order(@RequestBody Order order , @PathVariable  ObjectId productId){
       try{
           orderService.saveOrders(order ,  productId);
           return new ResponseEntity<>(order , HttpStatus.CREATED);
       } catch (RuntimeException e) {
           e.printStackTrace();
           log.error(e.getMessage());
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
    @GetMapping("/all")
    public  ResponseEntity<?> getAllOrders(){
        try{
            List<Product> all = orderService.findAll();
            return new ResponseEntity<>(all , HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
