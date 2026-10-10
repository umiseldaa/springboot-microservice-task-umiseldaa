package com.project.book_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.book_management.entity.BookEntity;

@Repository 
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    
    boolean existsByIsbn(String isbn);

    // SELECT EXISTS (SELECT 1 FROM books WHERE isbn = ? AND id <> ?)
    boolean existsByIsbnAndIdNot(String isbn, Long id);
}
