package com.project.ecommerce.services;

import com.project.ecommerce.exceptions.ProductNotFoundException;
import com.project.ecommerce.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(String productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product addNewProduct(Product product);
}