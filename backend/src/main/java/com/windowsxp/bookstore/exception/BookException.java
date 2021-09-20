package com.windowsxp.bookstore.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BookException extends RuntimeException{
    private final BookException.BookExceptionType bookExceptionType;

    public enum BookExceptionType {
        BOOK_NOT_EXIST, BOOK_DELETED
    }

    public static final Map<BookException.BookExceptionType, String> map = new HashMap<>() {{
        put(BookException.BookExceptionType.BOOK_NOT_EXIST, "书籍不存在");
        put(BookException.BookExceptionType.BOOK_DELETED, "书籍已被删除");
    }};

    public BookException(BookException.BookExceptionType bookExceptionType) {
        super(map.get(bookExceptionType));
        this.bookExceptionType = bookExceptionType;
    }
}
