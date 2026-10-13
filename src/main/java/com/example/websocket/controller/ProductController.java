package com.example.websocket.controller;

import com.example.websocket.model.Product;
import com.example.websocket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        return  productService.addProduct(product);
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@RequestParam Long id) {
        return productService.findById(id);
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestParam Long id,
                                 @RequestParam BigDecimal price) {
        return productService.updateProduct(id, price);
    }
}
