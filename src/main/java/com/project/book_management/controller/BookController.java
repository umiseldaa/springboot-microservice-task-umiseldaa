package com.project.book_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.book_management.utils.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.book_management.dto.BookRequest;
import com.project.book_management.dto.BookResponse;
import com.project.book_management.service.BookService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;

import com.project.book_management.dto.BookPatchRequest;




@RestController 
@Slf4j 
@RequestMapping("/api/books")
public class BookController {

    @Autowired 
    private BookService service;
    
    @GetMapping("/test")
    public String getMethodName() {
        return new String("<h3>API Book Management Running<h3>");
    }
    
    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> addBook(
        @Valid 
        @RequestBody BookRequest req) {
        
            BookResponse addBook = service.addBook(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Add book successfully with id " + addBook.id()));
    }

    @GetMapping("")
    public ApiResponse<List<BookResponse>> getAll() {
        return ApiResponse.success(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<BookResponse> getById(@PathVariable Long id) {
        return ApiResponse.success(service.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<String> update(@PathVariable Long id, @Valid  @RequestBody BookRequest req) {
        service.update(id, req);
        return ApiResponse.success("Update book successfully for id " + id);
    }

    @PatchMapping("/{id}")
    public ApiResponse<String> patch(@PathVariable Long id, @Valid  @RequestBody BookPatchRequest req) {
        service.patch(id, req);
        return ApiResponse.success("Partially update book successfully for id " + id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success("Deleted book successfully for id " + id);
    }
    
    
}
