package com.project.book_management.dto;

import java.time.LocalDate;

import com.project.book_management.entity.BookEntity;

public record BookResponse(
    Long id,
    String title,
    String author,
    String isbn,
    LocalDate publishedDate
) {
    public static BookResponse from(BookEntity book) {
        return new BookResponse(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn(),
            book.getPublishedDate()
        );
    }

}
