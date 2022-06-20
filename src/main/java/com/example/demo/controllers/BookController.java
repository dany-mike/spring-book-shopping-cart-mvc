package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.models.Book;
import com.example.demo.service.BookService;

@Controller
public class BookController {

    // @GetMapping("/books/{id}")
    // public Optional<Book> getProductById(@PathVariable Long id) {
    // return bookService.getBookById(id);
    // }

    // @PostMapping("/books")
    // public Book saveProduct(@RequestBody book) {
    // // return bookService.saveProduct;
    // }
}
