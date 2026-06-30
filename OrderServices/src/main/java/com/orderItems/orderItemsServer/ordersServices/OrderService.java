package com.orderItems.orderItemsServer.ordersServices;

import com.orderItems.orderItemsServer.client.ProductClients;
import com.orderItems.orderItemsServer.client.UserClient;
import com.orderItems.orderItemsServer.dto.Product;
import com.orderItems.orderItemsServer.entity.Order;
import com.orderItems.orderItemsServer.repository.OrdersRepositry;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrdersRepositry ordersRepositry;

    private final ProductClients productClients;
    private final UserClient userClient;

    public OrderService(ProductClients productClients, UserClient userClient) {
        this.productClients = productClients;
        this.userClient = userClient;
    }

    public Order saveOrders(Order order ,  String productId){
        Product product = productClients.getProductById(productId);
        String userName = userClient.getUserName();
        Optional<Order> existingOrderOpt = ordersRepositry.findByUserNameAndProductId(userName, productId);
        if(existingOrderOpt.isPresent()){
            Order existingOrder = existingOrderOpt.get();
            existingOrder.setQuantity(existingOrder.getQuantity() + 1);
           return ordersRepositry.save(existingOrder);
        }else {
            order.setProductId(productId);
            order.setProductName(product.getName());
            order.setProductPrice(product.getPrice());
            order.setQuantity(order.getQuantity() + 1);
            order.setUserName(userName);
           return ordersRepositry.save(order);
        }
    }

    public Optional<Order> findById(Long id){
       return ordersRepositry.findById(id);
    }


    public List<Order> findAllOrders(){
       return ordersRepositry.findAll();
    }

    public boolean deleteOrder(Order orders){
        Integer id = orders.getId();
      return ordersRepositry.deleteAllById(id);
    }
public List<Product> findAll(){
        return productClients.findAll();
}
}
