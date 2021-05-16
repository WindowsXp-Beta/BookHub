package com.windowsxp.bookstore.daoimpl;

import com.windowsxp.bookstore.dao.BookDao;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findOne(Integer id){
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }
}