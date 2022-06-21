package com.example.demo.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.service.UserServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class ShoppingCartRestController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/cart/add/{bid}/{qty}")
    public String addBookToCart(@PathVariable Long bid, @PathVariable Integer qty) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must be logged in to add this book to your shopping cart";
        }

        User user = userService.getCurrentUser(authentication);

        if (user == null) {
            return "You must be logged in to add this book to your shopping cart";
        }

        Integer addedQuantity = shoppingCartService.addBook(bid, qty, user);
        return addedQuantity + " item(s) of this product were added to your shopping cart.";
    }

    @PostMapping("/cart/update/{bid}/{qty}")
    public String updateQuantity(@PathVariable Long bid, @PathVariable Integer qty) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must be logged in to updat this book";
        }

        User user = userService.getCurrentUser(authentication);

        System.out.println("BID " + bid + " QTY: " + qty);

        if (user == null) {
            return "You must be logged in to add this book";
        }

        float subtotal = shoppingCartService.updateQuantity(bid, qty, user);
        return String.valueOf(subtotal);
    }

    @PostMapping("/cart/remove/{bid}")
    public String removeBook(@PathVariable Long bid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must be logged in to updat this book";
        }

        User user = userService.getCurrentUser(authentication);

        if (user == null) {
            return "You must be logged in to add this book";
        }

        shoppingCartService.removeBook(bid, user);

        return "The book has been remove from your cart";
    }

}
