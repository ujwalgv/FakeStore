package com.project.ecommerce.DTOs;

import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStorePostResponseDTO {
    private String id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct(FakeStorePostResponseDTO fakeStoreGetResponse){

            Product responseProduct = new Product();
            responseProduct.setId(fakeStoreGetResponse.getId());
            responseProduct.setTitle(fakeStoreGetResponse.getTitle());
            responseProduct.setPrice(fakeStoreGetResponse.getPrice()*1.0);
            responseProduct.setDescription(fakeStoreGetResponse.getDescription());
            responseProduct.setImage(fakeStoreGetResponse.getImage());
            Category responseCategory = new Category();
            responseCategory.setName(fakeStoreGetResponse.getCategory());
            responseProduct.setCategory(responseCategory);



        return responseProduct;

    }
}
