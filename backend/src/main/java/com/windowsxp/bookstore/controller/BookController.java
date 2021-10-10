package com.windowsxp.bookstore.controller;

import com.windowsxp.bookstore.dto.request.NewBookDTO;
import com.windowsxp.bookstore.dto.response.BookDTO;
import com.windowsxp.bookstore.dto.response.HomeDTO;
import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.service.BookService;
import com.windowsxp.bookstore.utils.LogUtil;
import com.windowsxp.bookstore.utils.sessionutils.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@AllArgsConstructor
public class BookController {

    final private BookService bookService;
    final private AtomicInteger pageView = new AtomicInteger();
//    private Long pageView;
    @GetMapping("/book")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> getBooks(@RequestParam int page,
                                      @RequestParam int pageSize) {
        try {
            LogUtil.debug(String.format("PV is %d", pageView.getAndIncrement()));
            PageDTO<BookDTO> books = bookService.getBooks(PageRequest.of(page, pageSize));
            return ResponseEntity.ok(new HomeDTO(books.getContent(), books.getSize(), pageView.get()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/book")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<?> addBook(@RequestBody NewBookDTO newBookDTO) {
        try {
            bookService.addBook(newBookDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/book")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<?> deleteBook(@RequestParam("id") Integer id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/book/detail")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> getBookDetail(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(bookService.getBookDetail(id));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/book/detail")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<?> editBook(@RequestBody Book book) {
        try {
            bookService.updateBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}