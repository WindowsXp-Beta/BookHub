package com.windowsxp.bookstore.serviceimpl;

import com.windowsxp.bookstore.dao.BookDao;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBook(Integer id){
        return bookDao.findBook(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public List<Book> getOnePageBooks(Integer range) {
        return bookDao.getOnePageBooks(range);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }
}
