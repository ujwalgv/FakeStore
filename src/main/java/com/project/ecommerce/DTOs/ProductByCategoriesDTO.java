package com.project.ecommerce.DTOs;

import com.project.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductByCategoriesDTO {
    List<Product> responseProductList;
    String status;
}
