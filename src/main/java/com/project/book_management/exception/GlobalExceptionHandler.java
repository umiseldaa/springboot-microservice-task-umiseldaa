package com.project.book_management.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.project.book_management.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestControllerAdvice 
public class GlobalExceptionHandler {

    
    @ExceptionHandler(DuplicateIsbnException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicate(DuplicateIsbnException ex) {
        log.warn("Duplicate ISBN: {}", ex.getMessage());
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }
    
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(BookNotFoundException ex) {
        log.warn("Book not found: {}", ex.getMessage());
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // catch error @validasi
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> err = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> err.putIfAbsent(e.getField(), e.getDefaultMessage()));
        log.warn("Validation failed: {}", err);
        return build(HttpStatus.BAD_REQUEST, err);
    }

    // invalid json payload
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidPayload(HttpMessageNotReadableException ex) {
        log.warn("Invalid JSON payload request body: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Invalid JSON payload or invalid value (dates must use yyy-MM-dd)");
    }

    // wrong type in path var or req param
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidPayload(MethodArgumentTypeMismatchException ex) {
        log.warn("Invalid type of value for parameter '{}'", ex.getName());
        return build(HttpStatus.BAD_REQUEST, "Invalid type of value for parameter '"+ex.getName()+"'");
    }

    // other
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleTypeMismatch(Exception ex) {
        if (ex instanceof ErrorResponse errorResponse) {
            log.warn("Request error: {}", ex.getMessage());
            return build(errorResponse.getStatusCode(), errorResponse.getBody().getDetail());
        }
        log.error("An unexpected error occurred: \n", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    private ResponseEntity<ApiResponse<Object>> build(HttpStatusCode status, Object data) {
        return ResponseEntity.status(status).body(ApiResponse.error(data));
    }
}
