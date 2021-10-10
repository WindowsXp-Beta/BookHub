package com.windowsxp.bookstore.service;


import com.windowsxp.bookstore.dto.request.NewBookDTO;
import com.windowsxp.bookstore.dto.response.BookDTO;
import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.Book;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book getBookDetail(Integer id);
    PageDTO<BookDTO> getBooks(Pageable pageable);
    void addBook(NewBookDTO newBookDTO);
    void updateBook(Book book);
    void deleteBook(Integer id);
}
