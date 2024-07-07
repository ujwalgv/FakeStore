package com.project.ecommerce.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {

//    {
//        title: 'test product',
//                price: 13.5,
//            description: 'lorem ipsum set',
//            image: 'https://i.pravatar.cc',
//            category: 'electronic'
//    }
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
