package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BookService;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "home";
    }

    @GetMapping("/myAccount")
    public String myAccount() {
        return "myAccount";
    }
}
