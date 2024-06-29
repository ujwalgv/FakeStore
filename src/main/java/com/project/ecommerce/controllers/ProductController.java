package com.project.ecommerce.controllers;

import com.project.ecommerce.DTOs.ProductResponse;
import com.project.ecommerce.exceptions.ProductNotFoundException;
import com.project.ecommerce.models.Product;
import com.project.ecommerce.services.FakeStoreProductService;
import com.project.ecommerce.services.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    FakeStoreProductService fakeStoreProductService;
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") String id){
        Product product = fakeStoreProductService.getSingleProduct(id);
        return product;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        List<Product> allProducts = fakeStoreProductService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("text") String text){
        List<Product> searchProducts = fakeStoreProductService.searchProducts(text);
        return searchProducts;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product requestProduct){
        Product product = fakeStoreProductService.addNewProduct(requestProduct);
        return product;
    }
}
