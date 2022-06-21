package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.qty = ?1 WHERE b.id = ?2")
    public void updateQuantity(Integer updatedQty, Long bookId);
}
