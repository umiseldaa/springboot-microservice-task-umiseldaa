package com.project.book_management.service;

import java.util.List;

import com.project.book_management.dto.BookPatchRequest;
import com.project.book_management.dto.BookRequest;
import com.project.book_management.dto.BookResponse;

public interface BookService {
    
    public BookResponse addBook(BookRequest req);
    public List<BookResponse> findAll();
    public BookResponse findById(Long id);
    public void update(Long id, BookRequest req);
    public void patch(Long id, BookPatchRequest req); //atau buat dto book patch req
    public void delete(Long id);

}
