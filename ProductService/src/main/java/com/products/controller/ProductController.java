package com.products.controller;

import com.products.entity.Product;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        try{
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/id/{productId}")
    public ResponseEntity<?> findById(@PathVariable String productId) {
        try{
            Product product = productService.findById(productId);
            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product ,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<Optional<Product>> findByName(@PathVariable String productName) {
        try {
            Optional<Product> product = productService.findByName(productName);
            if (product.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try{
            boolean b = productService.addItem(product);
            return  new ResponseEntity<>(b, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            Product product1 = productService.updateProduct(product);
            if(product1 == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteProduct(@RequestBody Product product) {
        try {
            productService.deleteById(product.getId());
            return new ResponseEntity<>(true ,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false , HttpStatus.BAD_REQUEST);
        }
    }

}
