package com.orderItems.orderItemsServer.entity;

import com.orderItems.orderItemsServer.dto.Product;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Indexed;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String productId;
    private String productName;
    private Double productPrice;
    private int quantity;
}
