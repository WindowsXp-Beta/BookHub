package com.windowsxp.booksearch.repository;

import com.windowsxp.bookhub.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String name);
}
