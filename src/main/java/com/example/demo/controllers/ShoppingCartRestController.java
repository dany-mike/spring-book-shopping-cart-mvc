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

        if (authentication instanceof AnonymousAuthenticationToken) {
            return "You must be logged in as a MySQL user  to add this book to your shopping cart";
        }

        User user = userService.getCurrentUser(authentication);

        if (user == null) {
            return "You must be logged in as a MySQL user to add this book to your shopping cart";
        }

        Integer addedQuantity = shoppingCartService.addBook(bid, qty, user);
        return addedQuantity + " item(s) of this product were added to your shopping cart.";
    }

    @PostMapping("/cart/update/{bid}/{qty}")
    public String updateQuantity(@PathVariable Long bid, @PathVariable Integer qty) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must be logged in to update this book";
        }

        User user = userService.getCurrentUser(authentication);

        if (user == null) {
            return "You must be logged in to update this book";
        }

        float subtotal = shoppingCartService.updateQuantity(bid, qty, user);
        return String.valueOf(subtotal);
    }

    @PostMapping("/cart/remove/{bid}")
    public String removeBook(@PathVariable Long bid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must be logged in to remove this book";
        }

        User user = userService.getCurrentUser(authentication);

        if (user == null) {
            return "You must be logged in to remove this book";
        }

        shoppingCartService.removeBook(bid, user);

        return "The book has been removed from your cart";
    }

    @PostMapping("/cart/confirm")
    public String confirmCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must be logged in to confirm your cart";
        }

        User user = userService.getCurrentUser(authentication);

        if (user == null) {
            return "You must be logged in to confirm your cart";
        }

        shoppingCartService.confirmOrder(user);

        return "Cart confirmed successfully";
    }

}
