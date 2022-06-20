package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(final Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book saveBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }
}