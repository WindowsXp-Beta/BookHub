package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookDao {
    Optional<Book> findBookById(Integer id);
    Page<Book> getBooks(Pageable pageable);
    void saveBook(Book book);
    void deleteBook(Integer id);
}