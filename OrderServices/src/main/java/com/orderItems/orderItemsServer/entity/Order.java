package com.orderItems.orderItemsServer.entity;

import com.orderItems.orderItemsServer.dto.Product;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.bson.types.ObjectId;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    private Long id;
    private String productId;
    private String productName;
    private Double productPrice;
}
