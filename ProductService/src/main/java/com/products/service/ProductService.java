package com.products.service;

import com.products.entity.Product;
import com.products.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public boolean addItem(Product product) {
        if (productRepository.existsById(product.getId())) {
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
            return true;
        }
        productRepository.save(product);
        return true;
    }
    public Product findById(String id) {
        Product product = productRepository.findById(id).get();
        return product;
    }
    public Optional<Product> findByName(String productName) {
        return productRepository.findByName(productName);
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public boolean deleteById(ObjectId id) {
         productRepository.findById(id).ifPresent(productRepository::delete);
        return true;
    }
    public Product updateProduct(Product product) {
        Optional<Product> productName = productRepository.findByName(product.getName());
        if(productName.isPresent()){
            product.setName(productName.get().getName());
            product.setPrice(productName.get().getPrice());
            product.setDescription(productName.get().getDescription());
            productRepository.save(product);
            return product;
        }
        return null;
    }

}
