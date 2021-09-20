package com.windowsxp.bookstore.service;


import com.windowsxp.bookstore.dto.request.NewBookDTO;
import com.windowsxp.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book getBookDetail(Integer id);
    Page<Book> getBooks(Pageable pageable);
    void addBook(NewBookDTO newBookDTO);
    void updateBook(Book book);
    void deleteBook(Integer id);
}
