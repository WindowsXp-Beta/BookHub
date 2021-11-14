package com.windowsxp.booksearch.serviceimpl;

import com.windowsxp.booksearch.dao.BookDao;
import com.windowsxp.booksearch.service.BookSearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {

    final private BookDao bookDao;

    @Override
    public String findAuthorByBookName(String name) {
        return bookDao.findAuthorByBookName(name);
    }
}
