package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.BookDao;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findBook(Integer id){
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<Book> ret = new ArrayList<>();
        for(Book book: bookList) {
            if(book.getInventory() >= 0) {
                ret.add(book);
            }
        }
        return ret;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.saveAndFlush(book);
    }

    @Override
    public List<Book> getOnePageBooks(Integer range) {
        return bookRepository.getOnePageBooks(range);
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.getById(id);
        book.setInventory(-1);
        bookRepository.saveAndFlush(book);
    }
}