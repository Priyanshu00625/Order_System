package com.orderItems.orderItemsServer.dto;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private ObjectId id;
    private String name;
    private String description;

    private Double price;

    private int quantity;
}
