package com.windowsxp.bookhub.backend.service;


import com.windowsxp.bookhub.backend.dto.request.NewBookDTO;
import com.windowsxp.bookhub.backend.dto.response.BookDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.book.entity.Book;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book getBookDetail(Integer id);
    PageDTO<BookDTO> getBooks(Pageable pageable);
    void addBook(NewBookDTO newBookDTO);
    void updateBook(Book book);
    void deleteBook(Integer id);
    PageDTO<BookDTO> getBooksByTag(String content);
}
