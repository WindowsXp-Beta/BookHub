package com.windowsxp.bookstore.controller;


import com.windowsxp.bookstore.dto.request.NewBookDTO;
import com.windowsxp.bookstore.dto.response.BookDTO;
import com.windowsxp.bookstore.dto.response.PageDTO;
import com.windowsxp.bookstore.entity.Book;
import com.windowsxp.bookstore.serviceimpl.SearchService;
import com.windowsxp.bookstore.utils.LogUtil;
import com.windowsxp.bookstore.utils.sessionutils.SessionUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final ModelMapper modelMapper;

    @GetMapping("/search/book")
    @SessionUtil.Auth(authType = SessionUtil.AuthType.PASS)
    public ResponseEntity<?> searchBook(@RequestParam String query,
                                        @RequestParam Integer page,
                                        @RequestParam Integer pageSize) {
        try {
            Page<Book> resultPage = searchService.searchBooks(query, page, pageSize);
            return ResponseEntity.ok(resultPage.stream().map((book) -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList()));
        } catch (Exception e) {
            LogUtil.error("An error occurred " + e.getClass() + " message: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
