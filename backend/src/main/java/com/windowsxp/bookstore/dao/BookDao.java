package com.windowsxp.bookstore.dao;

import com.windowsxp.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    Book findBook(Integer id);
    List<Book> getBooks();
}