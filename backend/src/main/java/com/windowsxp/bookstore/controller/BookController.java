package com.windowsxp.bookstore.controller;

import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.service.BookService;

import com.windowsxp.bookstore.utils.msgutils.Msg;
import com.windowsxp.bookstore.utils.msgutils.MsgCode;
import com.windowsxp.bookstore.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<Book> getBooks() {
        System.out.println("getBooks");
        return bookService.getBooks();
    }

    @RequestMapping("/getOnePageBooks")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public List<Book> getOnePageBooks(@RequestBody Map<String, String> params) {
        System.out.println("get One page books");
        Integer range = Integer.valueOf(params.get("key"));
        range = 16 * (range - 1) + 1;
        List<Book> result = bookService.getOnePageBooks(range);
        Iterator<Book> it = result.iterator();
        while(it.hasNext()) {
            Book book = it.next();
            if(book.getInventory() == -1) {
                it.remove();
            }
        }
        return result;
    }

    @RequestMapping("/getBook")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Book getBook(@RequestParam("id") Integer id) {
        System.out.println("getBook: " + id);
        return bookService.findBook(id);
    }

    @RequestMapping("/editBook")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Book editBook(
//            @RequestBody Map<String, String> params
            @RequestBody Book book,
            @RequestParam String extraNumber
    ) {
        System.out.println("editBook");
        System.out.println(extraNumber);
//        Integer bookId = Integer.valueOf(params.get("bookId"));
//        Book book  = bookService.findBook(bookId);

        //更新book
//        book.setName(params.get("name"));
//        book.setAuthor(params.get("author"));
//        book.setImage(params.get("image"));
//        book.setIsbn(params.get("isbn"));
//        book.setInventory(Integer.valueOf(params.get("inventory")));
//        book.setPrice(Integer.valueOf(params.get("price")));
//        book.setDescription(params.get("description"));

        bookService.addBook(book);
        return bookService.findBook(book.getId());
    }

    @RequestMapping("/addBook")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg addBook(@RequestBody Map<String, String> params) {
        System.out.println("addBook");
        Book book = new Book();
        //更新book
        book.setName(params.get("name"));
        book.setAuthor(params.get("author"));
        book.setImage(params.get("image"));
        book.setIsbn(params.get("isbn"));
        book.setType("test");
        book.setInventory(Integer.valueOf(params.get("inventory")));
        book.setPrice(Integer.valueOf(params.get("price")));
        book.setDescription(params.get("description"));
        bookService.addBook(book);

        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ADD_SUCCESS_BOOK_MSG);
    }

    @RequestMapping("/deleteBook")
    @CrossOrigin(value = "http://localhost:3000",maxAge = 1800,allowedHeaders = "*",allowCredentials="true")
    public Msg deleteBook(@RequestParam("id") Integer id) {
        System.out.println("delete Book " + id);
        bookService.deleteBook(id);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_BOOK_MSG);
    }
}