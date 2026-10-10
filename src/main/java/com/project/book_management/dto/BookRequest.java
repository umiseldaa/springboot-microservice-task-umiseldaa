package com.project.book_management.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record BookRequest(
    
    @NotBlank(message = "title is required")
    @Size(max = 255, message = "title must be at most 255 character")
    String title,

    @NotBlank(message = "author is required")
    @Size(max = 255, message = "author must be at most 255 character")
    String author,

    @NotBlank(message = "isbn is required")
    @Size(max = 20, message = "isbn must be at most 20 character")
    String isbn,

    @NotNull (message = "publishedDate is required")
    @PastOrPresent(message = "publishedDate cannot be in the future")
    LocalDate publishedDate

) {
    
}
