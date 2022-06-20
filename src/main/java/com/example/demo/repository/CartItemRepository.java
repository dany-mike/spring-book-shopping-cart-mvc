package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CartItem;
import com.example.demo.models.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public List<CartItem> findByUser(User user);
}