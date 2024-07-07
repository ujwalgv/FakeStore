package com.project.ecommerce.DTOs;

import com.project.ecommerce.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreGetCategoriesDTO {
    private String name;

    public Category toCategory(FakeStoreGetCategoriesDTO responseItem){
        Category category = new Category();
        category.setName(responseItem.getName());
        return category;
    }
}
