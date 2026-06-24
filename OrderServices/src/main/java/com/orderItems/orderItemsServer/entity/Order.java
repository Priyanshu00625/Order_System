package com.orderItems.orderItemsServer.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    private Long id;
    @Nonnull
    private Long productId;
    @Nonnull
    private Long productPrice;
    private String productName;

}
