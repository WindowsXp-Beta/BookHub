package com.windowsxp.bookhub.backend.dao;

import com.windowsxp.bookhub.backend.dto.response.BookDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.book.entity.Book;
import org.springframework.data.domain.Pageable;

public interface BookDao {
    Book findBookById(Integer id);
    PageDTO<BookDTO> getBooks(Pageable pageable);
    void saveBook(Book book);
    void deleteBook(Integer id);
}