package com.products.repository;

import com.products.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByName(String productName);

    boolean existsById(ObjectId id);

    Optional<Product> findById(ObjectId id);

}
