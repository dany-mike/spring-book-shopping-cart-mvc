package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Book;
import com.example.demo.models.CartItem;
import com.example.demo.models.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public List<CartItem> findByUser(User user);

    public CartItem findByUserAndBook(User user, Book book);

    @Query(value = "UPDATE CartItem c SET c.quantity = ? WHERE c.book.id = ?" + "AND c.user.id = ?", nativeQuery = true)
    @Modifying
    public void updateQuantity(Integer quantity, Long bookId, Long userId);
}