package com.project.ecommerce.services;

import com.project.ecommerce.DTOs.FakeStoreGetResponse;
import com.project.ecommerce.DTOs.FakeStorePostResponse;
import com.project.ecommerce.exceptions.ProductNotFoundException;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
@Primary
public class FakeStoreProductService implements ProductService{

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getSingleProduct(String productId){
        String url = "https://fakestoreapi.com/products/" + productId;
        FakeStoreGetResponse fakeStoreGetResponse = restTemplate.getForObject(url, FakeStoreGetResponse.class);
        Product product = fakeStoreGetResponse.toProduct(fakeStoreGetResponse);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products";

        FakeStorePostResponse[] fakeStorePostResponseList = restTemplate.getForObject(url,FakeStorePostResponse[].class);
        List<Product> allProductList = new ArrayList<>();
        for (FakeStorePostResponse fakeStorePostResponse: fakeStorePostResponseList){
            Product product = fakeStorePostResponse.toProduct(fakeStorePostResponse);
            allProductList.add(product);
        }
        return allProductList;

    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    public List<Product> searchProducts(String text) {
        return null;
    }
}

