package com.windowsxp.bookstore.repository;

import com.windowsxp.bookstore.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "from Book where id >= :range and id < :range + 16")
    List<Book> getOnePageBooks(@Param("range") Integer range);
}