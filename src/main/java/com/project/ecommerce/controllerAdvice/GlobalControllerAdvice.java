package com.project.ecommerce.controllerAdvice;

import com.project.ecommerce.DTOs.ErrorResponseDTO;
import com.project.ecommerce.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException e){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(e.getMessage() + " at Controller Advice");
        ResponseEntity<ErrorResponseDTO> error = new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        return error;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleExceptionClassError(Exception e){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(e.getMessage() + " at Controller Advice");
        ResponseEntity<ErrorResponseDTO> error = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return error;
    }
}
