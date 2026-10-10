package com.project.book_management.exception;

public class BookNotFoundException extends  RuntimeException {
    
    public BookNotFoundException(Long id) {
        super("Book not found for id " + id);
    }

}
