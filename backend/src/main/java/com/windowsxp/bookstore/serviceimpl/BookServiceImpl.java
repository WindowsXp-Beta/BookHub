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
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }
}
