package com.example.websocket.service;

import com.example.websocket.listeners.DatabaseChangeEvent;
import com.example.websocket.model.Product;
import com.example.websocket.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ApplicationEventPublisher publisher;

    public String addProduct(Product product) {
        productRepo.save(product);
        return "Product added successfully";
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }


    @Transactional
    public Product updateProduct(Long id, BigDecimal price) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setPrice(price);
        productRepo.save(product);
        publisher.publishEvent(new DatabaseChangeEvent(product,this,"Product","Update"));
        return product;
    }

}
