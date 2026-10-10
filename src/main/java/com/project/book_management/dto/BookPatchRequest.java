package com.project.book_management.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BookPatchRequest(
    @Pattern (regexp = ".*\\S.*", message = "title must not be blank")
    @Size(max = 255, message = "title must be at most 255 character")
    String title,

    @Pattern (regexp = ".*\\S.*", message = "author must not be blank")
    @Size(max = 255, message = "author must be at most 255 character")
    String author,

    @Pattern (regexp = ".*\\S.*", message = "isbn must not be blank")
    @Size(max = 20, message = "isbn must be at most 20 character")
    String isbn,

    @PastOrPresent(message = "publishedDate cannot be in the future")
    LocalDate publishedDate
) {
}
