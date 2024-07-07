package com.project.ecommerce.services;

import com.project.ecommerce.DTOs.FakeStoreGetCategoriesDTO;
import com.project.ecommerce.DTOs.FakeStoreGetResponseDTO;
import com.project.ecommerce.DTOs.FakeStorePostResponseDTO;
import com.project.ecommerce.DTOs.PostRequestDTO;
import com.project.ecommerce.exceptions.ProductNotFoundException;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.service.annotation.PostExchange;

import java.util.ArrayList;
import java.util.List;
@Service
@Primary
public class FakeStoreProductService implements ProductService{

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getSingleProduct(String productId) throws ProductNotFoundException{
        String url = "https://fakestoreapi.com/products/" + productId;
        FakeStoreGetResponseDTO fakeStoreGetResponseDTO = restTemplate.getForObject(url, FakeStoreGetResponseDTO.class);
        if (fakeStoreGetResponseDTO ==null){
            throw new ProductNotFoundException("product with id" + productId + " not found");

        }
        Product product = fakeStoreGetResponseDTO.toProduct(fakeStoreGetResponseDTO);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products";

        FakeStoreGetResponseDTO[] fakeStoreGetResponseDTOList = restTemplate.getForObject(url, FakeStoreGetResponseDTO[].class);
        List<Product> allProductList = new ArrayList<>();
        for ( FakeStoreGetResponseDTO fakeStoreGetResponseDTO : fakeStoreGetResponseDTOList){
            Product product = fakeStoreGetResponseDTO.toProduct(fakeStoreGetResponseDTO);
            allProductList.add(product);
        }
        return allProductList;

    }

    @Override
    public Product addNewProduct(PostRequestDTO postRequestDTO) {
        String url = "https://fakestoreapi.com/products";
        FakeStorePostResponseDTO savedProduct = restTemplate.postForObject(url, postRequestDTO, FakeStorePostResponseDTO.class );

        Product product = savedProduct.toProduct(savedProduct);
        return product;

    }


    public List<Category> getAllCategories(){
        String url = "https://fakestoreapi.com/products/categories";
        String[] responseCategories = restTemplate.getForObject(url, String[].class);
        List<Category> allCategories = new ArrayList<>();
        for (String responseItem: responseCategories){
            Category category = new Category();
            category.setName(responseItem);
            allCategories.add(category);
        }

        return allCategories;


    }

    @Override
    public Product updateProduct(PostRequestDTO postRequestDTO, String id) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostRequestDTO> entity = new HttpEntity<PostRequestDTO>(postRequestDTO, headers);


        ResponseEntity<FakeStorePostResponseDTO> responseDTO = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                FakeStorePostResponseDTO.class);

        return responseDTO.getBody().toProduct(responseDTO.getBody());
    }

    public  Product deleteProduct(String id) throws ProductNotFoundException {
        String url = "https://fakestoreapi.com/products/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostRequestDTO> entity = new HttpEntity<PostRequestDTO>(headers);

        try{
            ResponseEntity<FakeStorePostResponseDTO> responseDTO = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    entity,
                    FakeStorePostResponseDTO.class);

            if (responseDTO.getBody()==null){
                throw new ProductNotFoundException("Product now found");
            }
            return responseDTO.getBody().toProduct(responseDTO.getBody());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value()==404){
                throw new ProductNotFoundException("Product with " + id + " not found");
            }
            throw e;
        }

    }

    public List<Product> getProductsOfCategory(String categoryName) throws BadRequestException {
        if (categoryName=="" || categoryName==null){
            throw new BadRequestException();
        }
        String url = "https://fakestoreapi.com/products/category/" + categoryName;
        FakeStoreGetResponseDTO[] responseProductList = restTemplate.getForObject(url, FakeStoreGetResponseDTO[].class);

        List<Product> allProducts = new ArrayList<>();
        for (FakeStoreGetResponseDTO responseItem: responseProductList){
            Product product = responseItem.toProduct(responseItem);
            allProducts.add(product);
        }

        return allProducts;
    }
}

