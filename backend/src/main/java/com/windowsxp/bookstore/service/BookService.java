package com.windowsxp.bookstore.service;


import com.windowsxp.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book findBook(Integer id);
    List<Book> getBooks();
    void addBook(Book book);
    void deleteBook(Integer id);
}
