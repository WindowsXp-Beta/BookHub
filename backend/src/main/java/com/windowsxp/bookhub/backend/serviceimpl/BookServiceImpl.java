package com.windowsxp.bookhub.backend.serviceimpl;

import com.windowsxp.bookhub.backend.dao.BookDao;
import com.windowsxp.bookhub.backend.dto.request.NewBookDTO;
import com.windowsxp.bookhub.backend.dto.response.BookDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.backend.exception.BookException;
import com.windowsxp.bookhub.backend.service.BookService;
import com.windowsxp.bookhub.book.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public Book getBookDetail(Integer id){
        Optional<Book> optionalBook = bookDao.findBookById(id);
        if(optionalBook.isEmpty()) throw new BookException(BookException.BookExceptionType.BOOK_NOT_EXIST);
        else {
            Book book = optionalBook.get();
            if(book.getInventory() < 0) throw new BookException(BookException.BookExceptionType.BOOK_DELETED);
            else return book;
        }
    }

    @Override
    public PageDTO<BookDTO> getBooks(Pageable pageable) {
        return bookDao.getBooks(pageable);
    }

    @Override
    public void addBook(NewBookDTO newBookDTO) {
        Book book = new Book();
        book.setIsbn(newBookDTO.getIsbn());
        book.setName(newBookDTO.getName());
        book.setType(newBookDTO.getType());
        book.setAuthor(newBookDTO.getAuthor());
        book.setPrice(newBookDTO.getPrice());
        book.setDescription(newBookDTO.getDescription());
        book.setInventory(newBookDTO.getInventory());

        bookDao.saveBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }
}
