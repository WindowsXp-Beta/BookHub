package com.windowsxp.booksearch.controller;

import com.windowsxp.booksearch.service.BookSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookSearchController {
    final private BookSearchService bookSearchService;

    @GetMapping("/book-service")
    public ResponseEntity<?> searchBookByAuthor(@RequestParam String name){
        try{
            return ResponseEntity.ok(bookSearchService.findAuthByBookName(name));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
