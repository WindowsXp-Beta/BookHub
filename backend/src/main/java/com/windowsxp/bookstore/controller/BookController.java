package com.windowsxp.bookstore.controller;

import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public String getBooks() {
        System.out.println("getBooks");
        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Book getBook(@RequestParam("id") Integer id) {
        System.out.println("getBook: " + id);
        return bookService.findBookById(id);
    }
}