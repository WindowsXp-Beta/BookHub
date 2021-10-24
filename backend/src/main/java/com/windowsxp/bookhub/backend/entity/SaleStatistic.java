package com.windowsxp.bookhub.backend.entity;

import com.windowsxp.bookhub.book.entity.Book;
import lombok.Data;

@Data
public class SaleStatistic {
    private Book book;
    private Integer bookNumber;
    private Integer Sum;
}
