package com.project.ecommerce.DTOs;

import com.project.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    String message;
    Product product;
}
