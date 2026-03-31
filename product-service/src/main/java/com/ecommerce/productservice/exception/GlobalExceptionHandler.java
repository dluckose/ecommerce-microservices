package com.ecommerce.productservice.exception;

import com.ecommerce.productservice.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Catch specific AI configuration or connection issues
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleConfigurationError(IllegalStateException ex) {
        ApiError error = new ApiError(
                "Service Configuration Error: Please check AI API keys.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Catch-all for any other unexpected 500 errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralError(Exception ex) {
        ApiError error = new ApiError(
                "An unexpected error occurred. Please try again later.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}