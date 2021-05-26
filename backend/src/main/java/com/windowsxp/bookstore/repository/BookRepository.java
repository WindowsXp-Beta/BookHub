package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}