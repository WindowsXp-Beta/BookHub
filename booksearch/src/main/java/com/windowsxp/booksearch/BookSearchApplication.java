package com.windowsxp.booksearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.windowsxp.bookhub.book.entity")
public class BookSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookSearchApplication.class, args);
    }
}
