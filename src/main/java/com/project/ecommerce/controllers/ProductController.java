package com.project.ecommerce.controllers;

import com.project.ecommerce.DTOs.PostRequestDTO;
import com.project.ecommerce.DTOs.ProductByCategoriesDTO;
import com.project.ecommerce.DTOs.ProductResponseDTO;
import com.project.ecommerce.exceptions.ProductNotFoundException;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import com.project.ecommerce.services.FakeStoreProductService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    FakeStoreProductService fakeStoreProductService;
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") String id) throws ProductNotFoundException {

        Product product = fakeStoreProductService.getSingleProduct(id);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setMessage("Success");
        productResponseDTO.setProduct(product);

        ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<ProductResponseDTO>(productResponseDTO ,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        List<Product> allProducts = fakeStoreProductService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/products/categories")
    public List<Category> getCategories(){
        List<Category> allCategories = fakeStoreProductService.getAllCategories();
        return allCategories;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody PostRequestDTO requestProduct){
        Product product = fakeStoreProductService.addNewProduct(requestProduct);
        return product;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> updatePutProduct(@RequestBody PostRequestDTO postRequestDTO, @PathVariable("id") String id) throws ProductNotFoundException {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        Product resposeProduct = fakeStoreProductService.updateProduct(postRequestDTO, id);
        responseDTO.setProduct(resposeProduct);
        responseDTO.setMessage("Success");
        ResponseEntity<ProductResponseDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return response;

    }

    @GetMapping("/products/category/{name}")
    public ResponseEntity<ProductByCategoriesDTO> getProductsByCategory(@PathVariable("name") String name) throws BadRequestException {
        List<Product> responseList = fakeStoreProductService.getProductsOfCategory(name);
        ProductByCategoriesDTO responseDTO = new ProductByCategoriesDTO();
        responseDTO.setResponseProductList(responseList);
        responseDTO.setStatus("Success");
        ResponseEntity<ProductByCategoriesDTO> response= new ResponseEntity<ProductByCategoriesDTO>(responseDTO, HttpStatus.OK);
        return response;

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("id") String id) throws ProductNotFoundException {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        Product deletedProduct = fakeStoreProductService.deleteProduct(id);
        productResponseDTO.setMessage("Successfully Deleted");
        productResponseDTO.setProduct(deletedProduct);
        ResponseEntity<ProductResponseDTO> response = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        return response;
    }
}
