package com.products.service;

import com.products.entity.Product;
import com.products.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        product.setProductId(UUID.randomUUID().toString());
        productRepository.save(product);
        return true;
    }
    public void updateQunatity(Product product) {
        String b = productRepository.findByProductId(product.getProductId());
        if (!b.isEmpty()) {
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
        }
    }
    public Product findById(String id) {
        return productRepository.findById(id).get();
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
