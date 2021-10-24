package com.windowsxp.bookhub.backend.controller;

import com.windowsxp.bookhub.backend.dto.request.NewBookDTO;
import com.windowsxp.bookhub.backend.dto.response.BookDTO;
import com.windowsxp.bookhub.backend.dto.response.HomeDTO;
import com.windowsxp.bookhub.backend.dto.response.PageDTO;
import com.windowsxp.bookhub.backend.service.BookService;
import com.windowsxp.bookhub.backend.utils.LogUtil;
import com.windowsxp.bookhub.backend.utils.sessionutils.SessionUtil;
import com.windowsxp.bookhub.book.entity.Book;
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

    @DeleteMapping("/book/{bookId}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.ADMIN)
    public ResponseEntity<?> deleteBook(@PathVariable Integer bookId) {
        try {
            bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/book/{bookId}")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> getBookDetail(@PathVariable Integer bookId) {
        try {
            return ResponseEntity.ok(bookService.getBookDetail(bookId));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/book")
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