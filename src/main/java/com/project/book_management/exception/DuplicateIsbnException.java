package com.project.book_management.exception;

public class DuplicateIsbnException extends RuntimeException {
    
    public DuplicateIsbnException(String isbn) {
        super("A book already exists for ISBN " + isbn);
    }
}
