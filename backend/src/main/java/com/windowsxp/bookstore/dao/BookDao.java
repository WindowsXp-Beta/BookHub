package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.Book;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookDao {
    Optional<Book> findBookById(Integer id);
    PageDTO<Book> getBooks(Pageable pageable);
    void saveBook(Book book);
    void deleteBook(Integer id);
}