package com.ecommerce.userservice.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;    // Usually the API path (e.g., /api/users/10)
    private String errorCode;  // A machine-readable code (e.g., USER_404)

    // Default Constructor
    public ErrorDetails() {}

    // Parameterized Constructor
    public ErrorDetails(LocalDateTime timestamp, String message, String details, String errorCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
}
