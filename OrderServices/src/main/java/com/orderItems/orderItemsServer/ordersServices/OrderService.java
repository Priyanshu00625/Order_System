package com.orderItems.orderItemsServer.ordersServices;

import com.orderItems.orderItemsServer.client.ProductClients;
import com.orderItems.orderItemsServer.dto.Product;
import com.orderItems.orderItemsServer.entity.Order;
import com.orderItems.orderItemsServer.repository.OrdersRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepositry ordersRepositry;

    private final ProductClients productClients;

    public OrderService(ProductClients productClients) {
        this.productClients = productClients;
    }

    public boolean saveOrders(Order orders , ObjectId productId){
        Product product = productClients.getProductById(productId);
        orders.setProductId(product.getId().toString());
        orders.setProductName(product.getName());
        orders.setProductPrice(product.getPrice());
        ordersRepositry.save(orders);
        return true;
    }

    public List<Order> findAllOrders(){
       return ordersRepositry.findAll();
    }

    public boolean deleteOrder(Order orders){
        Long id = orders.getId();
      return ordersRepositry.deleteAllById(id);
    }
public List<Product> findAll(){
        return productClients.findAll();
}
}
