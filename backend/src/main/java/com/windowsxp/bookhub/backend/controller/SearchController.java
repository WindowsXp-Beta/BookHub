package com.windowsxp.bookhub.backend.controller;


import com.windowsxp.bookhub.backend.dto.response.BookDTO;
import com.windowsxp.bookhub.backend.serviceimpl.SearchService;
import com.windowsxp.bookhub.backend.utils.LogUtil;
import com.windowsxp.bookhub.backend.utils.sessionutils.SessionUtil;
import com.windowsxp.bookhub.book.entity.Book;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
