package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/myAccount")
    public String myAccount() {
        return "myAccount";
    }
}
