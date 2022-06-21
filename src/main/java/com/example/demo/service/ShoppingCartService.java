package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.models.CartItem;
import com.example.demo.models.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartItemRepository;

@Service
public class ShoppingCartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<CartItem> getCartItemsUser(User user) {
        return cartItemRepository.findByUser(user);
    };

    public Integer addBook(Long bookId, Integer quantity, User user) {
        Integer addedQuantity = quantity;

        Book book = bookRepository.findById(bookId).get();

        CartItem cartItem = cartItemRepository.findByUserAndBook(user, book);

        if (cartItem != null) {
            addedQuantity = quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setBook(book);
        }

        cartItemRepository.save(cartItem);

        return addedQuantity;
    }

    public float updateQuantity(Long bookId, Integer quantity, User user) {
        cartItemRepository.updateQuantity(quantity, bookId, user.getId());

        Book book = bookRepository.findById(bookId).get();

        float subtotal = book.getPrice() * quantity;
        return subtotal;
    }

    public void removeBook(Long bookId, User user) {
        cartItemRepository.deleteByUserAndBook(user.getId(), bookId);
    }

    public List<CartItem> confirmOrder(User user) {
        List<CartItem> cartItems = getCartItemsUser(user);

        System.out.println(cartItems);

        return cartItems;
    }
}
