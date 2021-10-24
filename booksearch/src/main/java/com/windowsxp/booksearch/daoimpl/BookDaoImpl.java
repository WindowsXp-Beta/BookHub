package com.windowsxp.booksearch.daoimpl;

import com.windowsxp.bookhub.book.entity.Book;
import com.windowsxp.bookhub.book.exception.BookException;
import com.windowsxp.booksearch.dao.BookDao;
import com.windowsxp.booksearch.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    final private BookRepository bookRepository;

    @Override
    public String findAuthorByBookName(String bookName) {
        Optional<Book> bookOptional = bookRepository.findByName(bookName);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            if(book.getInventory() == -1) throw new BookException(BookException.BookExceptionType.BOOK_DELETED);
            return book.getAuthor();
        } else {
            throw new BookException(BookException.BookExceptionType.BOOK_NOT_EXIST);
        }
    }
}
