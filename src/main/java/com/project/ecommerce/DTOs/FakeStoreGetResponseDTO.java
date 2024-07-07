package com.project.ecommerce.DTOs;

import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreGetResponseDTO {
    private String id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String category;

    public Product toProduct(FakeStoreGetResponseDTO fakeStoreGetResponseDTO){
        Product responseProduct = new Product();
        responseProduct.setId(fakeStoreGetResponseDTO.getId());
        responseProduct.setTitle(fakeStoreGetResponseDTO.getTitle());
        responseProduct.setPrice(fakeStoreGetResponseDTO.getPrice()*1.0);
        responseProduct.setDescription(fakeStoreGetResponseDTO.getDescription());
        responseProduct.setImage(fakeStoreGetResponseDTO.getImageUrl());
        Category responseCategory = new Category();
        responseCategory.setName(fakeStoreGetResponseDTO.getCategory());
        responseProduct.setCategory(responseCategory);

        return responseProduct;

    }
}
