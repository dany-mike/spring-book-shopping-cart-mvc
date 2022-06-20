package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CartItem;
import com.example.demo.models.User;
import com.example.demo.repository.CartItemRepository;

@Service
public class ShoppingCartService {
    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartItem> getCartItemsUser(User user) {
        return cartItemRepository.findByUser(user);
    };
}
