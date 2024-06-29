package com.project.ecommerce.DTOs;

import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FakeStorePostResponse {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private String category;

    public Product toProduct(FakeStorePostResponse fakeStoreGetResponse){

            Product responseProduct = new Product();
            responseProduct.setId(fakeStoreGetResponse.getId());
            responseProduct.setName(fakeStoreGetResponse.getName());
            responseProduct.setPrice(fakeStoreGetResponse.getPrice()*1.0);
            responseProduct.setDescription(fakeStoreGetResponse.getDescription());
            Category responseCategory = new Category();
            responseCategory.setName(fakeStoreGetResponse.getCategory());
            responseProduct.setCategory(responseCategory);



        return responseProduct;

    }
}
