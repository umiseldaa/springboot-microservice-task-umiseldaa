package com.project.book_management.entity;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor 
@Table(name = "books")
public class BookEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    // @CreationTimestamp 
    // @Column(name = "created_at", updatable = false)
    // private Instant createdAt;

    // @UpdateTimestamp
    // @Column(name = "modified_at")
    // private Instant modifiedAt;

    // @UpdateTimestamp 
    // @Column(name = "deleted_at")
    // private Boolean deletedAt;

    // @Column(name = "is_delete")
    // private Boolean deleted = false;

}
