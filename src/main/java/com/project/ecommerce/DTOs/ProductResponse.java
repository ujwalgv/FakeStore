package com.project.ecommerce.DTOs;

import com.project.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    String message;
    Product product;
}
