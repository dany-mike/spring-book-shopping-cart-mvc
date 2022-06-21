package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.CartItem;
import com.example.demo.models.User;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.service.UserServiceImpl;

@Controller
public class CartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/cart")
    public String book(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getCurrentUser(auth);
        List<CartItem> listCartItems = shoppingCartService.getCartItemsUser(user);

        model.addAttribute("items", listCartItems);
        model.addAttribute("title", "Shopping cart");
        return "cart";
    }

}
