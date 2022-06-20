package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.BookService;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books/{id}")
    public String book(Model model, @PathVariable Long id) {
        bookService.getBookById(id).ifPresent(b -> model.addAttribute("book", b));
        return "book";
    }
}
