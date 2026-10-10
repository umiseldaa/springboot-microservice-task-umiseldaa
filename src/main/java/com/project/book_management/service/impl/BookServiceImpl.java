package com.project.book_management.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.book_management.dto.BookPatchRequest;
import com.project.book_management.dto.BookRequest;
import com.project.book_management.dto.BookResponse;
import com.project.book_management.entity.BookEntity;
import com.project.book_management.exception.BookNotFoundException;
import com.project.book_management.exception.DuplicateIsbnException;
import com.project.book_management.repository.BookRepository;
import com.project.book_management.service.BookService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j 
@Transactional
public class BookServiceImpl implements BookService{

    @Autowired 
    private BookRepository repo;
    
    @Transactional
    @Override
    public BookResponse addBook(BookRequest req) {
        String isbn = req.isbn().trim();
        if (repo.existsByIsbn(isbn)) {
            throw new DuplicateIsbnException(isbn);
        }
        BookEntity book = new BookEntity();
        book.setTitle(req.title().trim());
        book.setAuthor(req.author().trim());
        book.setIsbn(isbn);
        book.setPublishedDate(req.publishedDate());

        BookEntity saved = repo.save(book);
        log.info("Add book with id {}", saved.getId());
        return BookResponse.from(saved);
    }
    
    @Override
    public List<BookResponse> findAll() {
        return repo.findAll().stream().map(BookResponse::from).toList();
    }

    @Override
    public BookResponse findById(Long id) {
        return BookResponse.from(getOrThrow(id));
    }

    @Transactional 
    @Override
    public void update(Long id, BookRequest req) {
        BookEntity book = getOrThrow(id);

        String title = req.title().trim();
        String author = req.author().trim();
        String isbn = req.isbn().trim();
        
        // boolean unchanged = book.getTitle().equals(title)
        //         && book.getAuthor().equals(author)
        //         && book.getIsbn().equals(isbn)
        //         && Objects.equals(book.getPublishedDate(), req.publishedDate());
        // if (unchanged) {
        //     log.info("Book id {} not updated, values are identical", id);
        //     return false;
        // }

        ensureIsbnAvailable(isbn, id);

        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPublishedDate(req.publishedDate());

        BookEntity saved = repo.save(book);
        log.info("Update book with id {}", id);
        // return true;
    }

    @Override
    public void patch(Long id, BookPatchRequest req) {
        BookEntity book = getOrThrow(id);

        if (req.title() != null) {
            book.setTitle(req.title().trim());
        }
        if (req.author() != null) {
            book.setAuthor(req.author().trim());
        }
        if (req.isbn() != null) {
            String isbn = req.isbn().trim();
            ensureIsbnAvailable(isbn, id);
            book.setIsbn(isbn);
        }
        if (req.publishedDate() != null) {
            book.setPublishedDate(req.publishedDate());
        }

        repo.save(book);
        log.info("Partial update book with id {}", id);
        // return BookResponse.from(saved);
    }

    @Override
    public void delete(Long id) {
        repo.delete(getOrThrow(id));
        log.info("Delete book with id {}", id);
    }

    private BookEntity getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    private void ensureIsbnAvailable(String isbn, Long currentId) {
        if(repo.existsByIsbnAndIdNot(isbn, currentId)) {
            throw new DuplicateIsbnException(isbn);
        }
    }



    
}
